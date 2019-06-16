package msu.ug

class Const {
    companion object {
        const val CUR_CHOICE_KEY = "current_choise"
        const val PATH_LEN_KEY = "path_len"

        const val DEFAULT_TAXON = "Nihil"

        const val MAP_TEXT_KEY = "text"
        const val JSON_TEXT_KEY = "text"
        const val TO_KEY = "to"
        const val TAXON_KEY = "taxon"

        const val SP_NAME = "my_shared_preferences"

        fun folderKey(folderIndex : Int) : String {
            return "folder_$folderIndex"
        }
    }
}