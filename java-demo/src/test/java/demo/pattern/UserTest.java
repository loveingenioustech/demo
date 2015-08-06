package demo.pattern;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest
{

    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void test()
    {
        User user1 = new User.UserBuilder("Terry", "Chen").age(30).build();

        assertEquals("Terry", user1.getFirstName());
        assertEquals("Chen", user1.getLastName());
    }

}
