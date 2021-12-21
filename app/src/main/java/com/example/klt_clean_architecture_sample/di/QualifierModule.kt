package com.example.klt_clean_architecture_sample.di

import javax.inject.Qualifier

object QualifierModule {
    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class Default

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class Io

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class Main

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class ApplicationScope

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class ViewScope
}