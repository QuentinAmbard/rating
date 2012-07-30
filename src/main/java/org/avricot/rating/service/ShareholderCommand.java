package org.avricot.rating.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.avricot.rating.model.company.Company;
import org.avricot.rating.model.company.ShareHolder;
import org.avricot.rating.utils.StringUtils;

public class ShareholderCommand {
    private final static int SHAREHOLDER_MAX_NUMBER = 7;
    private final List<SHCommand> shareholders = new ArrayList<SHCommand>();
    private boolean next;

    public ShareholderCommand() {

    }

    public ShareholderCommand(final Company company) {
        for (ShareHolder sh : company.getShareholders()) {
            shareholders.add(new SHCommand(sh));
        }
        // Fill with empty command to display forms.
        for (int i = shareholders.size(); i < SHAREHOLDER_MAX_NUMBER; i++) {
            shareholders.add(new SHCommand());
        }
        Collections.sort(shareholders, new Comparator<SHCommand>() {
            @Override
            public int compare(final SHCommand o1, final SHCommand o2) {
                if (o2.isEmpty()) {
                    return -1;
                }
                return (o1.getLastname() + o1.getFirstname()).compareToIgnoreCase(o2.getLastname() + o2.getFirstname());
            }
        });
    }

    public List<SHCommand> getShareholders() {
        return shareholders;
    }

    public static class SHCommand {
        private String firstname;
        private String lastname;
        private Float percent;

        public SHCommand() {
        }

        public SHCommand(final ShareHolder sh) {
            firstname = sh.getFirstname();
            lastname = sh.getLastname();
            percent = sh.getPercent();
        }

        public boolean isEmpty() {
            return !(StringUtils.hasText(firstname) || StringUtils.hasText(lastname) || percent != null);
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(final String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(final String lastname) {
            this.lastname = lastname;
        }

        public Float getPercent() {
            return percent;
        }

        public void setPercent(final Float percent) {
            this.percent = percent;
        }

        @Override
        public String toString() {
            return "SHCommand [firstname=" + firstname + ", lastname=" + lastname + ", percent=" + percent + "]";
        }
    }

    @Override
    public String toString() {
        return "ShareholderCommand [shareholders=" + shareholders + "]";
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(final boolean next) {
        this.next = next;
    }

}
