package paw.util.mail.conf;

public class URMailConfiguration extends ConfiguracionCorreo {

  public String getHost() {
    return "smtp.gmail.com";
  }

  public String getPort() {
    return "587";
  }

  public String getEnableStartTLS() {
    return "true";
  }

  public String getUser() {
    //throw new java.lang.IllegalArgumentException("Debes especificar un valor de usuario que devolver");
    return "pawaplicaciones@gmail.com";
  }

  public String getPassword() {
    //throw new java.lang.IllegalArgumentException("Debes especificar un valor de contraseña que devolver");
    return "a1s2d3f4g";
  }

  public String getAuth() {
    return "true";// Si en la UR se pone a true da error
  }
}