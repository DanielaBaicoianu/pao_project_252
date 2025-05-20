package obj;
class Vote{
    private User voter;
    private boolean isUp;
    Vote(User x, boolean isUp){
       voter=x;
       this.isUp=isUp;
    }
}