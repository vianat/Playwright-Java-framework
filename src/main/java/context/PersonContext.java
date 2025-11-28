package context;

public class PersonContext {
    private final ThreadLocal<String> randomFirstName = ThreadLocal.withInitial(() -> null);
    private final ThreadLocal<String> randomLastName = ThreadLocal.withInitial(() -> null);
    private final ThreadLocal<String> emailAddress = ThreadLocal.withInitial(() -> null);
//    private final ThreadLocal<Integer> someNumber = ThreadLocal.withInitial(() -> 1);

    public void setRandomFirstName(String name) {
        this.randomFirstName.set(name);
    }

    public void setRandomLastName(String name) {
        this.randomLastName.set(name);
    }

    public void setRandomEmail(String email) {
        this.emailAddress.set(email);
    }

    public String getRandomFirstName() {
        return randomFirstName.get();
    }

    public String getRandomLastName() {
        return randomLastName.get();
    }

    public String getRandomEmail() {
        return emailAddress.get();
    }
}
