package com.SpendControl.maxwell.SpendControl.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.RolesAllowed;

@Route("home")
@RolesAllowed("SCOPE_USER")
public class HomeView extends AppLayout {
    public HomeView() {
        // Cabeçalho
        H1 title = new H1("FinSight");
        title.getStyle().set("margin", "0");

        Button prefsButton = new Button("Preferências", e -> {
            Dialog prefsDialog = new Dialog();
            prefsDialog.add(new H4("Preferências do usuário"));
            // exemplo de conteúdo
            VerticalLayout prefsContent = new VerticalLayout();
            prefsContent.add(new Span("Aqui você pode ajustar suas preferências."));
            Button close = new Button("Fechar", ev -> prefsDialog.close());
            prefsContent.add(close);
            prefsDialog.add(prefsContent);
            prefsDialog.setWidth("400px");
            prefsDialog.open();
        });

        Button logoutButton = new Button("Logout", e -> {
            // navega para endpoint de logout; ajuste conforme sua implementação de segurança
            UI.getCurrent().getPage().setLocation("/logout");
        });

        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.setPadding(true);
        header.setAlignItems(Alignment.CENTER);
        header.add(title);
        header.expand(title);

        HorizontalLayout rightActions = new HorizontalLayout(prefsButton, logoutButton);
        rightActions.setSpacing(true);
        header.add(rightActions);

        addToNavbar(header);

        // Menu lateral (drawer)
        VerticalLayout menuLayout = new VerticalLayout();
        menuLayout.setPadding(false);
        menuLayout.setSpacing(false);
        menuLayout.setWidthFull();

        Anchor planosMensais = new Anchor("planos-mensais", "Planos mensais");
        planosMensais.getElement().getStyle().set("display", "block");
        planosMensais.getElement().getStyle().set("padding", "8px 12px");

        // Configurações com submenu (categorias de despesas)
        Accordion configuracoes = new Accordion();
        VerticalLayout configItems = new VerticalLayout();
        configItems.setPadding(false);
        configItems.setSpacing(false);
        configItems.setWidthFull();

        Anchor categorias = new Anchor("configuracoes/categorias", "Categorias de despesas");
        categorias.getElement().getStyle().set("display", "block");
        categorias.getElement().getStyle().set("padding", "8px 12px");

        configItems.add(categorias);
        configuracoes.add("Configurações", configItems);

        menuLayout.add(planosMensais, configuracoes);

        addToDrawer(menuLayout);
    }
}
