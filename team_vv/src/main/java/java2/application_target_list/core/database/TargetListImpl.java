package java2.application_target_list.core.database;

import org.springframework.stereotype.Component;


import java2.application_target_list.core.domain.Target;


import java.util.ArrayList;
import java.util.List;

@Component
public class TargetListImpl implements Database {

    List<Target> targetsList = new ArrayList<>();
    Long targetId = 0L;

    @Override
    public Long addTarget(Target target) {
        target.setId(targetId += 1);
        targetsList.add(target);
        return target.getId();
    }

    @Override
    public boolean deleteTarget(Long targetId) {
        if (isIdInTargetList(targetId)) {
            targetsList.remove(getTargetIndexFromListById(targetId));
            return true;
        }
        return false;
    }

    @Override
    public boolean changeTargetName(Long targetId, String newName) {
        if (isIdInTargetList(targetId)){
            targetsList.get(getTargetIndexFromListById(targetId)).setName(newName);
            return true;
        }
        return false;
    }

    @Override
    public boolean changeTargetDescription(Long targetId, String newDescription) {
        if (isIdInTargetList(targetId)){
            targetsList.get(getTargetIndexFromListById(targetId)).setDescription(newDescription);
            return true;
        }
        return false;
    }

    @Override
    public boolean changeTargetDeadline(Long targetId, int newDeadline) {
        if (isIdInTargetList(targetId)){
            targetsList.get(getTargetIndexFromListById(targetId)).setDeadline(newDeadline);
            return true;
        }
        return false;
    }

    public boolean isIdInTargetList(Long targetId) {
        for (Target tempTarget : targetsList) {
            if (tempTarget.getId().equals(targetId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Target> findByTargetName(String targetName) {
        List<Target> targets = new ArrayList<>();

        for (Target target : targetsList){
            if (target.getName().contains(targetName)){
                targets.add(target);
            }
        }
        return targets;
    }

    @Override
    public List<Target> findByTargetDescription(String targetDescription) {
        List<Target> targets = new ArrayList<>();

        for (Target target : targetsList){
            if (target.getDescription().contains(targetDescription)){
                targets.add(target);
            }
        }
        return targets;
    }

    private int getTargetIndexFromListById(Long targetId) {
        for (int i = 0; i < targetsList.size(); i++) {
            if (targetsList.get(i).getId().equals(targetId)) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public List<Target> getTargetsList() {
        return targetsList;
    }


}
