package msu.ug

class Const {
    companion object {
        const val STEPS_NUM_KEY = "steps_num"
        const val PATH_LEN_KEY = "path_len"
        const val IS_DESCRIPTION_KEY = "is_description"

        const val DEFAULT_TAXON = "Nihil"

        const val MAP_TEXT_KEY = "text"

        const val JSON_TEXT_KEY = "text"
        const val TO_KEY = "to"
        const val TAXON_KEY = "taxon"

        const val NAME_KEY = "russian_name"
        const val DESCRIPTION_KEY = "description"
        const val TERMINAL_KEY = "terminal"

        const val SP_NAME = "my_shared_preferences"

        fun folderKey(folderIndex : Int) : String {
            return "folder_$folderIndex"
        }

        fun stepKey(stepIndex : Int) : String {
            return "step_$stepIndex"
        }
    }
}