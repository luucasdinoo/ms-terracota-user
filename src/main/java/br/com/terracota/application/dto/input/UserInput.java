package br.com.terracota.application.dto.input;

public record UserInput(
        String username,
        String password,
        String name,
        String email,
        String phone
) {
    public static UserInput with(String username, String password, String name, String email, String phone){
        return new UserInput(username, password, name, email, phone);
    }
}
