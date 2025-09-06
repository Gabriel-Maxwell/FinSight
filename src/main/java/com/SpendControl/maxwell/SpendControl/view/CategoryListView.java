package com.SpendControl.maxwell.SpendControl.view;

import com.SpendControl.maxwell.SpendControl.domain.Category;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Route("categories")
@PermitAll
public class CategoryListView extends VerticalLayout {
    private final TextField nameFilter;
    private final ComboBox<String> typeFilter;
    private final Button searchButton;
    private final Button addButton;
    private final Grid<Category> grid;
    private final ListDataProvider<Category> dataProvider;

    public CategoryListView() {
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        nameFilter = new TextField();
        nameFilter.setPlaceholder("Nome");
        nameFilter.setClearButtonVisible(true);

        typeFilter = new ComboBox<>();
        typeFilter.setPlaceholder("Tipo");
        typeFilter.setAllowCustomValue(true);
        // opcional: setPossible types if known: typeFilter.setItems("INCOME", "EXPENSE");
        typeFilter.setClearButtonVisible(true);

        searchButton = new Button("Pesquisar", new Icon(VaadinIcon.SEARCH));
        searchButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        searchButton.addClickListener(e -> onSearch());

        addButton = new Button("Incluir categoria", new Icon(VaadinIcon.PLUS));
        addButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        addButton.addClickListener(e -> onAdd());

        HorizontalLayout topBar = new HorizontalLayout();
        topBar.setWidthFull();
        topBar.setAlignItems(Alignment.CENTER);
        topBar.add(nameFilter, typeFilter, searchButton, addButton);

        // Grid
        grid = new Grid<>(Category.class, false);
        grid.addColumn(Category::getName).setHeader("Nome").setFlexGrow(2);
        grid.addColumn(Category::getType).setHeader("Tipo").setFlexGrow(1);

        NumberFormat currencyFmt = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        grid.addColumn(cat -> formatBigDecimal(cat.getGoal(), currencyFmt)).setHeader("Goal").setFlexGrow(1);
        grid.addColumn(cat -> formatBigDecimal(cat.getSpent(), currencyFmt)).setHeader("Spent").setFlexGrow(1);

        grid.addColumn(new ComponentRenderer<>(cat -> {
            HorizontalLayout actions = new HorizontalLayout();
            actions.setSpacing(true);

            Button view = new Button(new Icon(VaadinIcon.SEARCH));
            view.getElement().setAttribute("title", "Ver detalhes");
            view.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            view.addClickListener(e -> onView(cat));

            Button edit = new Button(new Icon(VaadinIcon.EDIT));
            edit.getElement().setAttribute("title", "Editar");
            edit.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            edit.addClickListener(e -> onEdit(cat));

            Button delete = new Button(new Icon(VaadinIcon.TRASH));
            delete.getElement().setAttribute("title", "Remover");
            delete.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_TERTIARY);
            delete.addClickListener(e -> onDelete(cat));

            actions.add(view, edit, delete);
            return actions;
        })).setHeader("Ações").setFlexGrow(1);

        // datasource inicial vazio; conecte ao seu serviço/repositorio
        List<Category> initial = new ArrayList<>();
        dataProvider = new ListDataProvider<>(initial);
        grid.setDataProvider(dataProvider);

        // opcional: cabeçalho com filtros inline
        HeaderRow headerRow = grid.appendHeaderRow();
        // se quiser, podemos adicionar filtros nos headers; por enquanto usamos a barra superior

        Div gridContainer = new Div(grid);
        gridContainer.setSizeFull();

        add(topBar, gridContainer);
        expand(gridContainer);
    }

    private void onSearch() {
        // TODO: substituir pela busca real via service/repository
        String name = nameFilter.getValue() == null ? "" : nameFilter.getValue().trim();
        String type = typeFilter.getValue() == null ? "" : typeFilter.getValue().trim();

        // exemplo mock: filtra lista atual do dataProvider
        List<Category> all = new ArrayList<>(dataProvider.getItems());
        List<Category> filtered = new ArrayList<>();
        for (Category c : all) {
            boolean matchesName = name.isEmpty() || (c.getName() != null && c.getName().toLowerCase().contains(name.toLowerCase()));
            boolean matchesType = type.isEmpty() || (c.getType() != null && c.getType().equalsIgnoreCase(type));
            if (matchesName && matchesType) {
                filtered.add(c);
            }
        }
        dataProvider.getItems().clear();
        dataProvider.getItems().addAll(filtered);
        dataProvider.refreshAll();

        // Nota: idealmente recuperar do serviço:
        // List<Category> results = categoryService.search(name, type);
        // dataProvider.getItems().clear();
        // dataProvider.getItems().addAll(results);
        // dataProvider.refreshAll();
    }

    private void onAdd() {
        // TODO: abrir diálogo/form de inclusão ou navegar para página de criação
    }

    private void onView(Category cat) {
        // TODO: mostrar detalhes (dialog ou navegação)
    }

    private void onEdit(Category cat) {
        // TODO: abrir formulário de edição
    }

    private void onDelete(Category cat) {
        // TODO: confirmação e remoção via service
    }

    private String formatBigDecimal(BigDecimal value, NumberFormat fmt) {
        if (value == null) return "-";
        try {
            return fmt.format(value);
        } catch (Exception e) {
            return value.toString();
        }
    }
}
