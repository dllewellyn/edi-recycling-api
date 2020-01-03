package edi.recycling.points.api.interfaces


interface Uploader<T> {
    fun uploadSingleItem(item : T)
}