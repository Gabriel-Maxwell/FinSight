package com.SpendControl.maxwell.SpendControl.view;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.SpendControl.maxwell.SpendControl.domain.User;
import com.SpendControl.maxwell.SpendControl.service.AuthenticationService;
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
    private final AuthenticationService authService;

    public LoginView(AuthenticationService authService) {
        userName = new TextField("Username");
        userPassword = new PasswordField("Password");
        loginButton = new Button("Login");
        feedback = new Span();
        feedback.getStyle().set("color", "red");
        H1 title = new H1("FinSight - Login");
        this.authService = authService;

        loginButton.addClickListener(e -> login());

        add(title, userName, userPassword, loginButton, feedback);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setSizeFull();
    }

    private void login() {
        String username = userName.getValue();
        String password = userPassword.getValue();

        if (username.isEmpty() || password.isEmpty()) {
            feedback.setText("Please enter both username and password.");
            return;
        }

        try {
            // Here you would typically call a service to authenticate the user
            // For demonstration, we will just simulate a successful login
            // In a real application, replace this with actual authentication logic
            User user = User.builder()
                .email(username)
                .password(password)
                .build();
            Authentication authentication = authService.authenticateApp(user);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Redirect to home or dashboard view
            getUI().ifPresent(ui -> ui.navigate("home"));
        } catch (AuthenticationException e) {
            feedback.setText("Invalid username or password.");
            feedback.getStyle().set("color", "red");
        } catch (Exception e) {
            feedback.setText("An error has ocorred, please try again later.");
            feedback.getStyle().set("color", "red");
        }
    }



    
}
