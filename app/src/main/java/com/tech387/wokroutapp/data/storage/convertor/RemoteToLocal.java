package com.tech387.wokroutapp.data.storage.convertor;


import com.tech387.wokroutapp.data.storage.local.exercise.Exercise;
import com.tech387.wokroutapp.data.storage.local.exercise.ExerciseTag;
import com.tech387.wokroutapp.data.storage.local.Tag.Tag;
import com.tech387.wokroutapp.data.storage.local.shoppackage.ShopPackage;
import com.tech387.wokroutapp.data.storage.local.shoppackage.ShopPackageTag;
import com.tech387.wokroutapp.data.storage.local.workout.Workout;
import com.tech387.wokroutapp.data.storage.local.workout.WorkoutTag;
import com.tech387.wokroutapp.data.storage.remote.response.ExerciseResponse;
import com.tech387.wokroutapp.data.storage.remote.response.FormatResponse;
import com.tech387.wokroutapp.data.storage.remote.response.PackagesResponse;
import com.tech387.wokroutapp.data.storage.remote.response.TagResponse;
import com.tech387.wokroutapp.data.storage.remote.response.WorkoutResponse;
import com.tech387.wokroutapp.data.storage.remote.response.WorkoutTagResponse;

import java.util.ArrayList;
import java.util.List;

public class RemoteToLocal {

    private static final String TAG = RemoteToLocal.class.getSimpleName();

    /**
     * we redict response from networking (exercise)
     *
     * @param exerciseResponses -> object wich we get exercise
     * @return -> new create list, which have all needed data
     */
    public static List<Exercise> exerciseConverter(List<ExerciseResponse> exerciseResponses) {

        List<Exercise> exercises = new ArrayList<>();

        for (ExerciseResponse e : exerciseResponses) {

            String videoUrl = "";

            for (FormatResponse f : e.getFormats()) {
                if (f.getType().equals("mp4")) {
                    videoUrl = f.getSource();
                    break;
                }
            }


            exercises.add(
                    new Exercise(
                            e.getId(),
                            e.getRawName(),
                            e.getName(),
                            e.getThumbnail(),
                            videoUrl,
                            e.getMusclesInvolved()
                    )
            );
        }
        return exercises;
    }

    /**
     * we redict response from networking (workout)
     *
     * @param workoutResponses -> object wich we get workout
     * @return -> new create list, which have all needed data
     */
    public static List<Workout> workoutConverter(List<WorkoutResponse> workoutResponses) {
        List<Workout> workouts = new ArrayList<>();

        for (WorkoutResponse w : workoutResponses) {
            workouts.add(
                    new Workout(w.getId(),
                            w.getName(),
                            w.getDescription(),
                            w.getDuration()
                    )
            );
        }

        return workouts;
    }

    /**
     * we redict response from networking (packages)
     *
     * @param packagesResponses -> object wich we get workout
     * @return -> new create list, which have all needed data
     */
    public static List<ShopPackage> shopPackagesConverter(List<PackagesResponse> packagesResponses) {
        List<ShopPackage> shopPackages = new ArrayList<>();

        for (PackagesResponse p : packagesResponses) {
            shopPackages.add(
                    new ShopPackage(
                            p.getId(),
                            p.getThumbnail(),
                            p.getName(),
                            p.getDescription()
                    )
            );
        }

        return shopPackages;
    }

    /**
     * we redict response from networking (tags)
     *
     * @param tagResponses -> object wich we get Tags
     * @return -> new create list, which have all needed data
     */
    public static List<Tag> tagConverter(List<TagResponse> tagResponses) {
        List<Tag> tags = new ArrayList<>();

        for (TagResponse t : tagResponses) {
            tags.add(
                    new Tag(
                            t.getId(),
                            t.getName()
                    )
            );
        }
        return tags;
    }

    /**
     * in this case we create table exercise_tag_table and send from exercise_table id and tag
     *
     * @param exerciseId -> get id from exercise
     * @param tags       ->get all tags from exercise
     * @return -> new create list, which have all needed data
     */
    public static List<ExerciseTag> exerciseTagConverter(long exerciseId, List<Integer> tags) {
        List<ExerciseTag> exerciseTags = new ArrayList<>();

        for (long tagId : tags) {
            exerciseTags.add(new ExerciseTag(exerciseId, tagId));
        }

        return exerciseTags;
    }


    /**
     * adding data in workout-tags-table
     * @param workoutId -> id from workout-table
     * @param workoutTagResponses -> adding id and type
     * @return
     */
        public static List<WorkoutTag> workoutTagsConverter(long workoutId, List<WorkoutTagResponse> workoutTagResponses) {
        List<WorkoutTag> workoutTags = new ArrayList<>();

     for(WorkoutTagResponse w : workoutTagResponses){
             workoutTags.add(new WorkoutTag(workoutId, w.getId(), w.getType()));
     }


        return workoutTags;
    }

    /**
     * adding data in shoppackage-tags-table
     * @return
     */
    public static List<ShopPackageTag> shopPackageTagConverter(long shopPackageID, List<Integer> tags) {
        List<ShopPackageTag> shopPackageTags = new ArrayList<>();

        for (long tagId : tags) {
            shopPackageTags.add(new ShopPackageTag(shopPackageID, tagId));
        }

        return shopPackageTags;
    }

}