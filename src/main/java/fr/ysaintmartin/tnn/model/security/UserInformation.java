package fr.ysaintmartin.tnn.model.security;

public record UserInformation(String email, String username, String creationDate, String updateDate,
                              String lastLoginDate) {
}
