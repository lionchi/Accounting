package ru.gavri.accounting.web.pkentity;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.haulmont.cuba.web.theme.HaloTheme;
import ru.gavri.accounting.entity.*;

import javax.inject.Inject;
import java.util.Map;
import com.haulmont.cuba.gui.data.Datasource;

public class PkEntityEdit extends AbstractEditor<PkEntity> {
    @Inject
    private Table<DisplayEntity> displaysTable;
    @Inject
    private Table<HddEntity> hardDisksTable;
    @Inject
    private Table<NetworkInterfaceEntity> networkInterfacesTable;
    @Inject
    private Table<VideoCardEntity> videoCardsTable;
    @Inject
    private ComponentsFactory componentsFactory;

    @Override
    public void init(Map<String, Object> params) {
        displaysTable.setItemClickAction(new BaseAction("opedEditor") {
            @Override
            public void actionPerform(Component component) {
                openEditor("accounting$DisplayEntity.edit", displaysTable.getSingleSelected(), WindowManager.OpenType.THIS_TAB);
            }
        });
        hardDisksTable.setItemClickAction(new BaseAction("opedEditor") {
            @Override
            public void actionPerform(Component component) {
                openEditor("accounting$HddEntity.edit", hardDisksTable.getSingleSelected(), WindowManager.OpenType.THIS_TAB);
            }
        });
        videoCardsTable.setItemClickAction(new BaseAction("opedEditor") {
            @Override
            public void actionPerform(Component component) {
                openEditor("accounting$VideoCardEntity.edit", videoCardsTable.getSingleSelected(), WindowManager.OpenType.THIS_TAB);
            }
        });
        networkInterfacesTable.setItemClickAction(new BaseAction("opedEditor") {
            @Override
            public void actionPerform(Component component) {
                openEditor("accounting$NetworkInterfaceEntity.edit", networkInterfacesTable.getSingleSelected(), WindowManager.OpenType.THIS_TAB);
            }
        });
    }

    public Component generateNameCpuField(Datasource datasource, String fieldId) {
        TextField textField = componentsFactory.createComponent(TextField.class);
        textField.setDatasource(datasource, fieldId);
        textField.setStyleName(HaloTheme.TEXTAREA_BORDERLESS);
        return textField;
    }

    public Component generateIdentifierField(Datasource datasource, String fieldId) {
        TextField textField = componentsFactory.createComponent(TextField.class);
        textField.setDatasource(datasource, fieldId);
        textField.setStyleName(HaloTheme.TEXTAREA_BORDERLESS);
        return textField;
    }

    public Component generateProcessorIDField(Datasource datasource, String fieldId) {
        TextField textField = componentsFactory.createComponent(TextField.class);
        textField.setDatasource(datasource, fieldId);
        textField.setStyleName(HaloTheme.TEXTAREA_BORDERLESS);
        return textField;
    }

    public Component generateLogicalProcessorCountField(Datasource datasource, String fieldId) {
        TextField textField = componentsFactory.createComponent(TextField.class);
        textField.setDatasource(datasource, fieldId);
        textField.setStyleName(HaloTheme.TEXTAREA_BORDERLESS);
        return textField;
    }

    public Component generatePhysicalProcessorCountField(Datasource datasource, String fieldId) {
        TextField textField = componentsFactory.createComponent(TextField.class);
        textField.setDatasource(datasource, fieldId);
        textField.setStyleName(HaloTheme.TEXTAREA_BORDERLESS);
        return textField;
    }

    public Component generateMotherboardManufacturerField(Datasource datasource, String fieldId) {
        TextField textField = componentsFactory.createComponent(TextField.class);
        textField.setDatasource(datasource, fieldId);
        textField.setStyleName(HaloTheme.TEXTAREA_BORDERLESS);
        return textField;
    }

    public Component generateMotherboardSerialNumberField(Datasource datasource, String fieldId) {
        TextField textField = componentsFactory.createComponent(TextField.class);
        textField.setDatasource(datasource, fieldId);
        textField.setStyleName(HaloTheme.TEXTAREA_BORDERLESS);
        return textField;
    }

    public Component generateVersionBiosField(Datasource datasource, String fieldId) {
        TextField textField = componentsFactory.createComponent(TextField.class);
        textField.setDatasource(datasource, fieldId);
        textField.setStyleName(HaloTheme.TEXTAREA_BORDERLESS);
        return textField;
    }

    public Component generateCurrentLocationField(Datasource datasource, String fieldId) {
        TextField textField = componentsFactory.createComponent(TextField.class);
        textField.setDatasource(datasource, fieldId);
        textField.setStyleName(HaloTheme.TEXTAREA_BORDERLESS);
        return textField;
    }

    public Component generateTargetLocationField(Datasource datasource, String fieldId) {
        TextField textField = componentsFactory.createComponent(TextField.class);
        textField.setDatasource(datasource, fieldId);
        textField.setStyleName(HaloTheme.TEXTAREA_BORDERLESS);
        return textField;
    }
}