package com.example.inclassexamples_w20;

public class Contact {

    //Android Studio hint: to create getter and setter, put mouse on variable and click "alt+insert"
    protected String email, name;
    protected long id;

    /**Constructor:*/
    public Contact(String n, String e, long i)
    {
        name =n;
        email = e;
        id = i;
    }

    public void update(String n, String e)
    {
        name = n;
        email = e;
    }

    /**Chaining constructor: */
    public Contact(String n, String e) { this(n, e, 0);}


    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }


}
