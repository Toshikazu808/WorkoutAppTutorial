package com.kannomedia.workoutapp

object Constants {
    fun defaultExerciseList(): Array<Exercise> {
        return arrayOf(
            Exercise(1, "Abdominal Crunches", R.drawable.ic_abdominal_crunch),
            Exercise(2, "High Knees", R.drawable.ic_high_knees_running_in_place),
            Exercise(3, "Jumping Jacks", R.drawable.ic_jumping_jacks),
            Exercise(4, "Lunges", R.drawable.ic_lunge),
            Exercise(5, "Plank", R.drawable.ic_plank),
            Exercise(6, "Push-up", R.drawable.ic_push_up),
            Exercise(7, "Push-up Rotation", R.drawable.ic_push_up_and_rotation),
            Exercise(8, "Side Plank", R.drawable.ic_side_plank),
            Exercise(9, "Squat", R.drawable.ic_squat),
            Exercise(10, "Chair Steps", R.drawable.ic_step_up_onto_chair),
            Exercise(11, "Chair Dips", R.drawable.ic_triceps_dip_on_chair),
            Exercise(12, "Wall Squat", R.drawable.ic_wall_sit)
        )
    }
}