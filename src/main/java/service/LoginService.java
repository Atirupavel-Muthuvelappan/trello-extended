package service;


public class LoginService {
    private String email;
    private String password;

    LoginService() {
    }

   public LoginService(String email, String password) {

        this.email = email;
        this.password = password;
    }

    public boolean[] validateUser() {
        boolean[] validity= new boolean[2];
        String arr[] = new Database(email).checkUser();
        if (arr[0] != null && arr[1] != null) {
            if ( password.equals(arr[0])) {
                validity[0]=true;validity[1]=true;
                return validity;
            } else
                validity[0]=true;validity[1]=false;
                return   validity;
        } else { validity[0]=false;validity[1]=false;
           return  validity;

        }
    }
}