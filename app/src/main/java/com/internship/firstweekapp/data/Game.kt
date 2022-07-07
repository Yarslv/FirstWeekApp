package com.internship.firstweekapp.data

object Game {
    val scenes = arrayListOf(SceneSet().apply {
        label = "start"
        actions = arrayListOf(
            Scene().apply {
                scene_bg = "lecturehall.jpg"
                eff = "fade"
                actions.add(SimpleText("It's only when I hear the sounds of shuffling feet and supplies being put away that I realize that the lecture's over."))
                actions.add(SimpleText("Professor Eileen's lectures are usually interesting, but today I just couldn't concentrate on it."))
                actions.add(SimpleText("I've had a lot of other thoughts on my mind...thoughts that culminate in a question."))
                actions.add(SimpleText("It's a question that I've been meaning to ask a certain someone."))
            },
            Scene().apply {
                scene_bg = "uni.jpg"
                eff = "fade"
                actions.add(SimpleText("When we come out of the university, I spot her right away."))
                actions.add(Show("dissolve", "sylvie green normal.png"))
                actions.add(SimpleText("I've known Sylvie since we were kids. She's got a big heart and she's always been a good friend to me."))
                actions.add(SimpleText("But recently... I've felt that I want something more."))
                actions.add(SimpleText("More than just talking, more than just walking home together when our classes end."))
                actions.add(
                    MenuText(
                        mapOf(
                            "To ask her right away." to "rightaway",
                            "To ask her later." to "later"
                        )
                    )
                )
            }
        )
    })
}

class SceneSet {
    var label = ""
    var actions = arrayListOf<Scene>()
}

class Scene(
    var scene_bg: String = "",
    var eff: String = "",

    val actions: ArrayList<BaseText> = arrayListOf<BaseText>()
)

open class BaseText(var text: String = "")
class SimpleText(textt: String) : BaseText(textt)
class MarkingText(var label: String = "") : BaseText()
class MenuText(var answMap: Map<String, String> = mapOf<String, String>()) : BaseText()
class Show(var eff: String = "", textt: String) : BaseText(textt)

