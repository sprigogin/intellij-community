// !ADD_KOTLIN_API
package test

import kotlinApi.KotlinClassAbstractProperty

class KotlinClassAbstractPropertyImpl : KotlinClassAbstractProperty() {
    override var isVisible: Boolean = false
        private set

    private fun test() {
        isVisible = true
    }
}
