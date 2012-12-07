package admin;

import java.util.Comparator;
import java.util.Timer;

public class CompetitorState {

    private boolean logged;
    private String user;
    private boolean submitting = false;
    private long submittingTime;
    private String idProblemSubmitting;
    private Timer timer;

    public CompetitorState(String user, boolean isSubmitting, long submittingTime, String idProblemSubmitting, Timer timer) {
        this.user = user;
        this.submitting = isSubmitting;
        this.submittingTime = submittingTime;
        this.idProblemSubmitting = idProblemSubmitting;
        this.timer = timer;
    }

    public CompetitorState(String user, boolean isSubmitting, long submittingTime, String idProblemSubmitting) {
        this.user = user;
        this.submitting = isSubmitting;
        this.submittingTime = submittingTime;
        this.idProblemSubmitting = idProblemSubmitting;
    }

    @Override
    public boolean equals(Object obj) {
        CompetitorState cs = (CompetitorState) obj;
        return this.logged == cs.logged;
    }

    static class ComparatorComp implements Comparator {

        public int compare(Object o1, Object o2) {
            CompetitorState a = (CompetitorState) o1;
            CompetitorState b = (CompetitorState) o2;
            return (a.isLogged() && !b.isLogged()) ? 1 : b.isLogged() ? 0 : -1;
        }
    }

    public String getIdProblemSubmitting() {
        return idProblemSubmitting;
    }

    public boolean isSubmitting() {
        return submitting;
    }

    public long getSubmittingTime() {
        return submittingTime;
    }

    public Timer getTimer() {
        return timer;
    }

    public String getUser() {
        return user;
    }

    public void setSubmittingTime(long submittingTime) {
        this.submittingTime = submittingTime;
    }

    public void setSubmitting(boolean isSubmitting) {
        this.submitting = isSubmitting;
    }

    public void setIdProblemSubmitting(String idProblemSubmitting) {
        this.idProblemSubmitting = idProblemSubmitting;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public boolean isLogged() {
        return logged;
    }
}
