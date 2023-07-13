package ingsis.snippetmanager.util

class Util {

    companion object {
        fun validateField(field: String?): Boolean {
            if (field == null) return false
            return field.split(" ").size == 1
        }
    }

}
