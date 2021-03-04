public class API {

  @Timed
  public void call1() {
    sleepTight(250);
  }

  private void sleepTight(int i) {
    try {
      Thread.sleep(i);
    } catch (InterruptedException e) {

    }
  }

  @Timed
  public String call2() {
    sleepTight(500);
    return "call2";
  }

  @Timed
  public String call3(String s) {
    sleepTight(750);
    call1();
    return "call3";
  }
}
