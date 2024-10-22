package github.alfonsojaen.view;


import github.alfonsojaen.model.User;

public class ControllerConversation {
    private User loggedInUser;
    private String selectedContact;

    // Método para configurar los datos iniciales de la conversación
    public void setConversationData(User user, String contact) {
        this.loggedInUser = user;
        this.selectedContact = contact;
        System.out.println("Usuario logueado: " + loggedInUser.getGmail());
        System.out.println("Conversando con: " + selectedContact);
        // Aquí puedes agregar la lógica para iniciar la conversación
    }

}