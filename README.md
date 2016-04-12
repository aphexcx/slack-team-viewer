# slack-team-viewer
the best got durned team viewer for slack

## Super cool libraries I used
#### [retrolambda](https://github.com/evant/gradle-retrolambda) 
Because Java 8 support (lambdas ❤) is the only tolerable way to write Java in the year of our lord 2016. [I love this library so much that I wrote a blog post about it](http://www.aphex.cx/use_java_8_on_android_with_a_little_hacking_/).
#### [ReactiveX - rxJava and rxAndroid](https://github.com/ReactiveX/RxJava) 
Throw away those decrepit, memory-leaking `AsyncTasks`. Get rid of those dumb `Timer`s. Trash your silly event buses (`otto` is *so* 2014). ReactiveX Observables are here, and they're the best thing since sliced bread.
#### [retrofit2](https://github.com/square/retrofit)
The best way to consume APIs on Android. Even has Gson built in to decode JSON from the API, but I decided to get even fancier and use...
#### [moshi](https://github.com/square/moshi) 
An awesome little JSON data binder. Give it POJOs and JSON, and it'll automagically translate between the two. Better than Gson.
#### [fj - Functional Java](http://www.functionaljava.org/) 
For cool Option monads. Some fields in JSON are missing. Optional, in other words. By defining them as Options they can be safely used anywhere without callers having to remember to null-check the hell out of you. By the way, I had to add a little adapter for **moshi** to correctly serialize/deserialize these, which was fun.
#### [butterknife](http://jakewharton.github.io/butterknife/)
Who the heck wants to type `findViewById` anymore? I don't and ButterKnife's compile-time viewbinding is so easy and fast that nobody should ever use findViewById ever again. (At least until Android marshmallow's databinding becomes a thing).
#### [recyclerview-animators](https://github.com/wasabeef/recyclerview-animators) 
A nifty lib to animate items inside recyclerViews in accordance with [the Google Material Design spec](https://www.google.com/design/spec/animation/meaningful-transitions.html#meaningful-transitions-hierarchical-timing) for recycler view animations. **FUN FACT:** [I'm a contributor on this!](https://github.com/wasabeef/recyclerview-animators/pull/66) I improved the animations so that items appear one by one instead of all at once. 