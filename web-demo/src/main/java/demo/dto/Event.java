package demo.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Robin Long
 */
public class Event implements Serializable
{
    /**
     * Version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private int id = 0;
    /**
     * title
     */
    private String title = null;
    /**
     * title_pound
     */
    private String titlePound = null;
    /**
     * start_date
     */
    private java.util.Date startDate = null;

    public Event()
    {
    }

    public Event(int id, String title, String titlePound, Date startDate)
    {
        super();
        this.id = id;
        this.title = title;
        this.titlePound = titlePound;
        this.startDate = startDate;
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle()
    {
        return this.title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(final String title)
    {
        this.title = title;
    }

    public String getTitlePound()
    {
        return titlePound;
    }

    public void setTitlePound(final String titlePound)
    {
        this.titlePound = titlePound;
    }

    public java.util.Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(final java.util.Date startDate)
    {
        this.startDate = startDate;
    }

    public String toString()
    {
        return new ToStringBuilder(this).appendSuper(super.toString()).append("id", this.id)
                .append("title", this.title).append("title_pound", this.titlePound)
                .append("start_date", this.startDate).toString();
    }
}
