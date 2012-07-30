package org.avricot.rating.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.avricot.rating.model.company.Company;
import org.avricot.rating.model.company.Manager;
import org.avricot.rating.utils.StringUtils;

//TODO meme chose que Shareholdercommand.
public class ManagerCommand {
    private final static int MANAGER_MAX_NUMBER = 5;
    private final List<MCommand> managers = new ArrayList<MCommand>();
    private boolean next;

    public ManagerCommand() {

    }

    public ManagerCommand(final Company company) {
        for (Manager m : company.getManagers()) {
            managers.add(new MCommand(m));
        }
        // Fill with empty command to display forms.
        for (int i = managers.size(); i < MANAGER_MAX_NUMBER; i++) {
            managers.add(new MCommand());
        }
        Collections.sort(managers, new Comparator<MCommand>() {
            @Override
            public int compare(final MCommand o1, final MCommand o2) {
                if (o2.isEmpty()) {
                    return -1;
                }
                return (o1.getLastname() + o1.getFirstname()).compareToIgnoreCase(o2.getLastname() + o2.getFirstname());
            }
        });
    }

    public List<MCommand> getManagers() {
        return managers;
    }

    public static class MCommand {
        private String firstname;
        private String lastname;
        private String function;

        public MCommand() {
        }

        public MCommand(final Manager m) {
            firstname = m.getFirstname();
            lastname = m.getLastname();
            function = m.getFunction();
        }

        public boolean isEmpty() {
            return !(StringUtils.hasText(firstname) || StringUtils.hasText(lastname) || StringUtils.hasText(function));
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

        public String getFunction() {
            return function;
        }

        public void setFunction(final String function) {
            this.function = function;
        }

        @Override
        public String toString() {
            return "SHCommand [firstname=" + firstname + ", lastname=" + lastname + ", function=" + function + "]";
        }
    }

    @Override
    public String toString() {
        return "ShareholderCommand [managers=" + managers + "]";
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(final boolean next) {
        this.next = next;
    }

}
