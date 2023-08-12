package cfg;


import cfg.infrastructure.PersistenceWorkers.def.PersistenceWorker;
import cfg.infrastructure.factories.impl.Factory;
import cfg.op.def.question.GetNextIdQuestion;
import cfg.po.def.User;

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
		
		User user = Factory.create(User.class);
		
		user.setAge(12);
	
//		GetNextIdQuestion qr = Factory.create(GetNextIdQuestion.class);
//
//		PersistenceWorker<User> pwUser = Factory.createPw(User.class);
//		User user = Factory.create(User.class);
//		
//		user.setId(100);
//		
//		System.out.println(pwUser.read(user));
//		
//		for(int i=0; i<100; i++) {
//			user.setAge(RandomUtility.getAge(20, 60));
//			user.setName(RandomUtility.getRandomName());
//			user.setSurname(RandomUtility.getRandomSurname());
//			user.setUserId(qr.getNextId().toString());
//			pwUser.store(user);
//		}

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
