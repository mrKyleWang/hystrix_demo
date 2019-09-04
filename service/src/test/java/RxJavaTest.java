import org.junit.Test;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * @author KyleWang
 * @version 1.0
 * @date 2019年09月04日
 */
public class RxJavaTest {

	@Test
	public void test() {

		Observable<String> observable = Observable.create((Observable.OnSubscribe<String>) subscriber -> {
			int count = 1;
			while (true) {
				try {
					System.out.println(Thread.currentThread().getName() + " : observable on next");
					Thread.sleep(1000);
					subscriber.onNext(count++ + "");
				} catch (Exception e) {
					subscriber.onError(e);
				}
			}
		}).subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread());

		Subscriber<String> subscriber = new Subscriber<String>() {
			@Override
			public void onCompleted() {
				System.out.println(Thread.currentThread().getName() + " : subscriber on completed");
			}

			@Override
			public void onError(Throwable throwable) {
				System.out.println(Thread.currentThread().getName() + " : subscriber on error");
			}

			@Override
			public void onNext(String s) {
				System.out.println(Thread.currentThread().getName() + " : subscriber on next，msg:" + s);
			}
		};
		observable.subscribe(subscriber);


		while (true){

		}
	}
}
