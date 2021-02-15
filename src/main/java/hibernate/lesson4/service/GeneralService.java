package hibernate.lesson4.service;

import hibernate.lesson4.model.Seance;
import hibernate.lesson4.model.UserType;

public class GeneralService {

    public void verificationAdmin() throws Exception {
        if (Seance.getUser() == null || Seance.getUser().getUserType() != UserType.ADMIN) {
            throw new Exception("Недостаточно прав для выполнения данной операции");
        }
    }

    public void verification() throws Exception {
        if (Seance.getUser() == null) {
            throw new Exception("Для выполнения данной операции необходимо войти в систему");
        }
    }
}