package agdi;

import agdi.helpers.RandomHelper;
import agdi.infrastructure.PersistenceWorkers.def.PersistenceWorker;
import agdi.infrastructure.factories.impl.Factory;
import agdi.op.def.question.GetNextIdQuestion;
import agdi.po.def.User;

/**
 * @author dizisa
 *
 */
public class MainClass {
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		Factory.getInstance();
	
		GetNextIdQuestion qr = Factory.create(GetNextIdQuestion.class);

		PersistenceWorker<User> pwUser = Factory.createPw(User.class);
		User user = Factory.create(User.class);
		
		for(int i=0; i<100; i++) {
			user.setAge(RandomHelper.getAge(20, 60));
			user.setName(RandomHelper.getRandomName());
			user.setSurname(RandomHelper.getRandomSurname());
			user.setUserId(qr.getNextId());
			pwUser.store(user);
		}

//
//
//		List<User> u = new ArrayList<User>();
//		for (int i = 0; i < qr.getNextId(); i++) {
//
//			user.setUserId(i);
//			u.add(pwUser.read(user));
//		}
//
//		for (int i = 0; i < qr.getNextId(); i++) {
//
//			user.setUserId(i);
//			u.add(pwUser.read(user));
//		}
//		u.forEach(q -> System.out
//				.println(q.getUserId() + " " + q.getName() + " " + q.getSurname() + ", " + q.getAge().toString()));

//		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
//		Scheduler scheduler = schedulerFactory.getScheduler();
//
//		JobDetail jobDetail = JobBuilder.newJob().ofType(ImageRecognition.class).withIdentity("Simple-job-name").build();
//		SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger().
//				withIdentity("Simple-trigger-name").forJob(jobDetail).
//					withSchedule(SimpleScheduleBuilder.simpleSchedule().
//						withIntervalInMilliseconds(100).withRepeatCount(5)).build();
//		
//		scheduler.scheduleJob(jobDetail ,simpleTrigger);
//
//		scheduler.start();
//
//		ImageRecognition imageRecognition = new ImageRecognitionImpl();
//		imageRecognition.run();
	
	}
}
