package ru.gavri.accounting.web.screens;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import ru.gavri.accounting.entity.HddEntity;
import ru.gavri.accounting.entity.PkEntity;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import com.haulmont.cuba.gui.components.Component;

public class Logging extends AbstractWindow {
    @Inject
    private CollectionDatasource<HddEntity, Long> hddEntitiesDs;
    @Inject
    private Table<HddEntity> hddEntitiesTable;
    @Inject
    private TextField searchField;

    @Override
    public void ready() {
        hddEntitiesTable.setStyleProvider((entity, property) -> {
            if (property == null) {
                // style for row
                if (!entity.getIsFormatted()) {
                    return "no-formatted";
                }
            } else if (property.equals("isFormatted")) {
                // style for column "isFormatted"
            }
            return null;
        });
        hddEntitiesTable.setItemClickAction(new BaseAction("openEditor") {
            @Override
            public void actionPerform(Component component) {
                openEditor("accounting$HddEntity.edit", hddEntitiesTable.getSingleSelected(), WindowManager.OpenType.THIS_TAB)
                        .addCloseWithCommitListener(() -> hddEntitiesDs.refresh());
            }
        });
    }

    public void onSearchButtonClick() {
        String search = searchField.getValue().toString();
        List<HddEntity> hddExcludeEntityList = new ArrayList<>();
        List<HddEntity> items = new ArrayList<>(hddEntitiesDs.getItems());
        for (HddEntity item : items) {
            PkEntity pk = item.getPk();
            if (!pk.getModelPk().toLowerCase().contains(search.toLowerCase()) && !pk.getName().toLowerCase().contains(search.toLowerCase()) &&
                    !item.getModel().toLowerCase().contains(search.toLowerCase()) && !item.getSerialNumber().toLowerCase().contains(search.toLowerCase())) {
                hddExcludeEntityList.add(item);
            }
        }
        hddExcludeEntityList.forEach(hddEntity -> hddEntitiesDs.excludeItem(hddEntity));
    }

    public void onRefresh(Component source) {
        if (searchField.getValue() != null) {
            searchField.setValue(null);
        }
        hddEntitiesDs.refresh();
    }
}