package demo.service;

import java.util.List;

import demo.dao.EventDao;
import demo.dao.EventDaoHibernateImpl;
import demo.dto.Event;

public class EventServiceImpl implements EventService {

	private EventDao eventDao = new EventDaoHibernateImpl();

	public void saveEvent(Event event) {
		System.out.println("Enter saveEvent Service");
		EventDao dao = new EventDaoHibernateImpl();
		try {
			dao.saveEvent(event);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("End saveEvent Service");
	}

	@Override
	public List<Event> getEventList() {
		return eventDao.getEventList();
	}

	@Override
	public List<Event> getEventList(int pageNumber, int pageSize) {
		return eventDao.getEventList(pageNumber, pageSize);
	}

}
