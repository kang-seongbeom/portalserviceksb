

public class DaoFactory {

    public UserDao getUserDao() {
        return new UserDao(getConncetionMaker());
    }

    public JejuConnectionMaker getConncetionMaker() {
        return new JejuConnectionMaker();
    }
}
