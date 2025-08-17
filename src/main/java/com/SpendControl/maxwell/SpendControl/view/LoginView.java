package com.SpendControl.maxwell.SpendControl.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("login")
@AnonymousAllowed
public class LoginView extends VerticalLayout{
    private TextField userName;
    private PasswordField userPassword;
    private Button loginButton;
    private Span feedback;
    
    public LoginView() {
        userName = new TextField("Username");
        userPassword = new PasswordField("Password");
        loginButton = new Button("Login");
        feedback = new Span();
        feedback.getStyle().set("color", "red");
        H1 title = new H1("FinSight - Login");

        loginButton.addClickListener(e -> login());

        add(title, userName, userPassword, loginButton, feedback);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setSizeFull();
    }

    private void login() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }



    
}
