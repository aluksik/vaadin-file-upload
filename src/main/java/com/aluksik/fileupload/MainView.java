package com.aluksik.fileupload;

import com.aluksik.fileupload.domains.LiniaCSV;
import com.aluksik.fileupload.services.LiniaCSVService;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

@Route
public class MainView extends VerticalLayout {

    private LiniaCSVService liniaCSVService;

    private MemoryBuffer buffer = new MemoryBuffer();
    private Upload upload = new Upload(buffer);
    private Grid<LiniaCSV> liniaCSVGrid = new Grid<>();

    public MainView(LiniaCSVService liniaCSVService) {
        this.liniaCSVService = liniaCSVService;
        initView();
    }

    private void initView() {
        setSizeFull();
        add(upload, liniaCSVGrid);

        liniaCSVGrid.addColumn(LiniaCSV::getId);
        liniaCSVGrid.addColumn(LiniaCSV::getLinia);

        upload.addSucceededListener(event -> {
            InputStreamReader reader = new InputStreamReader(buffer.getInputStream());
            Stream<String> lines = new BufferedReader(reader).lines();

            lines.forEach(liniaCSVService::addLine);

            liniaCSVGrid.setItems(liniaCSVService.findAll());
            Notification.show("Upload file: " + event.getFileName());
        });
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        liniaCSVGrid.setItems(liniaCSVService.findAll());
    }
}
