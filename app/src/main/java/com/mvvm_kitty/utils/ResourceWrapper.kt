package io.bm.gl4ss3s_app.utils

/*L'intérêt de cette encapsulation est double :

Notification à l'utilisateur de l'état de chargement des données
Gestion des cas d'erreurs HTTP*/

class ResourceWrapper<T> private constructor(val status: Status, val data: T?, val message: String? = null, val code: Int? = null) {
    companion object {
        fun <T> success(data: T): ResourceWrapper<T> {
            return ResourceWrapper(Status.SUCCESS, data)
        }

        fun <T> error(message : String, code : Int = 400, data: T?): ResourceWrapper<T> {
            return ResourceWrapper(Status.ERROR, data, message, code)
        }

        fun <T> loading(data: T?): ResourceWrapper<T> {
            return ResourceWrapper(Status.LOADING, data)
        }
    }
}