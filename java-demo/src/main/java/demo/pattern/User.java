package demo.pattern;

public class User
{
    private final String firstName; // required

    private final String lastName; // required

    private final int age; // optional

    private final String phone; // optional

    private final String address; // optional

    private User(UserBuilder builder)
    {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    /**
     * @return the firstName
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * @return the age
     */
    public int getAge()
    {
        return age;
    }

    /**
     * @return the phone
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     * @return the address
     */
    public String getAddress()
    {
        return address;
    }

    public static class UserBuilder
    {
        private final String firstName;
        private final String lastName;
        private int age;
        private String phone;
        private String address;

        public UserBuilder(String firstName, String lastName)
        {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public UserBuilder age(int age)
        {
            this.age = age;
            return this;
        }

        public UserBuilder phone(String phone)
        {
            this.phone = phone;
            return this;
        }

        public UserBuilder address(String address)
        {
            this.address = address;
            return this;
        }

        public User build()
        {
            return new User(this);
        }
    }
}