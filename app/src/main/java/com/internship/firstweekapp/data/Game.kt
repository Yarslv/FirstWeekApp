package com.internship.firstweekapp.data

import android.content.Context
import com.internship.firstweekapp.R
import com.internship.firstweekapp.util.*

class Game(context: Context) {
    val scenes = mapOf(
        context.getString(R.string.start_scene) to SceneSet().apply {
            actions = arrayListOf(
                Scene().apply {
                    scene_bg = Constants.LECTUREHALL_BG_FILENAME
                    eff = Anim.FADE
                    actions.add(AuthorText(context.getString(R.string.start1)))
                    actions.add(AuthorText(context.getString(R.string.start2)))
                    actions.add(AuthorText(context.getString(R.string.start3)))
                    actions.add(AuthorText(context.getString(R.string.start4)))
                },
                Scene().apply {
                    scene_bg = Constants.UNI_BG_FILENAME
                    eff = Anim.FADE
                    actions.add(AuthorText(context.getString(R.string.start5)))
                    actions.add(ShowImage(Anim.FADE, Constants.S_GREEN_NORMAL_IMAGE_FILENAME))
                    actions.add(AuthorText(context.getString(R.string.start6)))
                    actions.add(AuthorText(context.getString(R.string.start7)))
                    actions.add(AuthorText(context.getString(R.string.start8)))
                    actions.add(
                        MenuText(
                            mapOf(
                                context.getString(R.string.start10) to JumpToSceneSet(
                                    context.getString(
                                        R.string.rightaway_scene_name
                                    )
                                ),
                                context.getString(R.string.start11) to JumpToSceneSet(
                                    context.getString(
                                        R.string.later_scene
                                    )
                                )
                            )
                        )
                    )
                }
            )
        },
        context.getString(R.string.rightaway_scene_name) to SceneSet().apply {
            actions = arrayListOf(
                Scene().apply {
                    scene_bg = Constants.UNI_BG_FILENAME
                    actions.add(ShowImage(name = Constants.S_GREEN_SMILE_IMAGE_FILENAME))
                    actions.add(
                        DialogText(
                            context.getString(R.string.Sylvie_name), context.getString(
                                R.string.rightaway1
                            )
                        )
                    )
                    actions.add(
                        DialogText(
                            context.getString(R.string.Me_name),
                            context.getString(R.string.rightaway2)
                        )
                    )
                    actions.add(AuthorText(context.getString(R.string.rightaway3)))
                    actions.add(
                        DialogText(
                            context.getString(R.string.Sylvie_name),
                            context.getString(R.string.rightaway4)
                        )
                    )
                    actions.add(
                        DialogText(
                            context.getString(R.string.Me_name),
                            context.getString(R.string.rightaway5)
                        )
                    )
                    actions.add(
                        DialogText(
                            context.getString(R.string.Sylvie_name),
                            context.getString(R.string.rightaway6)
                        )
                    )
                },
                Scene().apply {
                    scene_bg = Constants.MEADOW_BG_FILENAME
                    eff = Anim.FADE
                    actions.add(AuthorText(context.getString(R.string.rightaway7)))
                    actions.add(AuthorText(context.getString(R.string.rightaway8)))
                    actions.add(AuthorText(context.getString(R.string.rightaway9)))
                    actions.add(
                        DialogText(
                            context.getString(R.string.Me_name),
                            context.getString(R.string.rightaway10)
                        )
                    )
                    actions.add(ShowImage(Anim.FADE, Constants.S_GREEN_SMILE_IMAGE_FILENAME))
                    actions.add(AuthorText(context.getString(R.string.rightaway11)))
                    actions.add(AuthorText(context.getString(R.string.rightaway12)))
                    actions.add(
                        DialogText(
                            context.getString(R.string.Me_name),
                            context.getString(R.string.rightaway13)
                        )
                    )
                    actions.add(
                        DialogText(
                            context.getString(R.string.Me_name),
                            context.getString(R.string.rightaway14)
                        )
                    )
                    actions.add(ShowImage(name = Constants.S_GREEN_SURPRISED_IMAGE_FILENAME))
                    actions.add(AuthorText(context.getString(R.string.rightaway15)))
                    actions.add(AuthorText(context.getString(R.string.rightaway16)))
                    actions.add(ShowImage(name = Constants.S_GREEN_SMILE_IMAGE_FILENAME))
                    actions.add(
                        DialogText(
                            context.getString(R.string.Sylvie_name),
                            context.getString(R.string.rightaway17)
                        )
                    )
                    actions.add(
                        MenuText(
                            mapOf(
                                context.getString(R.string.rightaway18) to JumpToSceneSet(
                                    context.getString(
                                        R.string.game_scene_name
                                    )
                                ),
                                context.getString(R.string.rightaway19) to JumpToSceneSet(
                                    context.getString(
                                        R.string.book_scene_name
                                    )
                                )
                            )
                        )
                    )

                }
            )
        },
        context.getString(R.string.game_scene_name) to SceneSet().apply {
            actions = arrayListOf(
                Scene().apply {
                    scene_bg = Constants.MEADOW_BG_FILENAME
                    actions.add(
                        DialogText(
                            context.getString(R.string.Me_name),
                            context.getString(R.string.game1)
                        )
                    )
                    actions.add(
                        DialogText(
                            context.getString(R.string.Me_name),
                            context.getString(R.string.game2)
                        )
                    )
                    actions.add(
                        DialogText(
                            context.getString(R.string.Me_name),
                            context.getString(R.string.game3)
                        )
                    )
                    actions.add(
                        DialogText(
                            context.getString(R.string.Sylvie_name),
                            context.getString(R.string.game4)
                        )
                    )
                    actions.add(
                        DialogText(
                            context.getString(R.string.Me_name),
                            context.getString(R.string.game5)
                        )
                    )
                    actions.add(
                        DialogText(
                            context.getString(R.string.Me_name),
                            context.getString(R.string.game6)
                        )
                    )
                    actions.add(ShowImage(name = Constants.S_GREEN_NORMAL_IMAGE_FILENAME))
                    actions.add(
                        DialogText(
                            context.getString(R.string.Sylvie_name),
                            context.getString(R.string.game7)
                        )
                    )
                    actions.add(
                        DialogText(
                            context.getString(R.string.Me_name),
                            context.getString(R.string.game8)
                        )
                    )
                    actions.add(JumpToSceneSet(context.getString(R.string.marry_scene)))
                }
            )
        },
        context.getString(R.string.book_scene_name) to SceneSet().apply {
            actions = arrayListOf(
                Scene().apply {
                    scene_bg = Constants.MEADOW_BG_FILENAME
                    actions.add(
                        DialogText(
                            context.getString(R.string.Me_name),
                            context.getString(R.string.book1)
                        )
                    )
                    actions.add(ShowImage(name = Constants.S_GREEN_SURPRISED_IMAGE_FILENAME))
                    actions.add(
                        DialogText(
                            context.getString(R.string.Sylvie_name),
                            context.getString(R.string.book2)
                        )
                    )
                    actions.add(
                        DialogText(
                            context.getString(R.string.Me_name),
                            context.getString(R.string.book3)
                        )
                    )
                    actions.add(
                        DialogText(
                            context.getString(R.string.Sylvie_name),
                            context.getString(R.string.book4)
                        )
                    )
                    actions.add(
                        DialogText(
                            context.getString(R.string.Me_name),
                            context.getString(R.string.book5)
                        )
                    )
                    actions.add(ShowImage(name = Constants.S_GREEN_SMILE_IMAGE_FILENAME))
                    actions.add(
                        DialogText(
                            context.getString(R.string.Sylvie_name),
                            context.getString(R.string.book6)
                        )
                    )
                    actions.add(
                        DialogText(
                            context.getString(R.string.Me_name),
                            context.getString(R.string.book7)
                        )
                    )
                    actions.add(
                        DialogText(
                            context.getString(R.string.Sylvie_name),
                            context.getString(R.string.book8)
                        )
                    )
                    actions.add(JumpToSceneSet(context.getString(R.string.marry_scene)))
                }
            )
        },
        context.getString(R.string.marry_scene) to SceneSet().apply {
            actions = arrayListOf(
                Scene().apply {
                    scene_bg = Constants.BLACK_BG_FILENAME
                    actions.add(AuthorText(context.getString(R.string.marry1)))
                },
                Scene().apply {
                    scene_bg = Constants.CLUB_BG_FILENAME
                    actions.add(AuthorText(context.getString(R.string.marry2)))

                    actions.add(AuthorText(context.getString(R.string.marry3)))
                    actions.add(AuthorText(context.getString(R.string.marry4)))
                    actions.add(ShowImage(Anim.FADE, Constants.S_BLUE_NORMAL_IMAGE_FILENAME))
                    actions.add(
                        DialogText(
                            context.getString(R.string.Sylvie_name),
                            context.getString(R.string.marry5)
                        )
                    )
                    actions.add(
                        DialogText(
                            context.getString(R.string.Me_name),
                            context.getString(R.string.marry6)
                        )
                    )
                    actions.add(ShowImage(name = Constants.S_BLUE_GIGGLE_IMAGE_FILENAME))
                    actions.add(
                        DialogText(
                            context.getString(R.string.Sylvie_name),
                            context.getString(R.string.marry7)
                        )
                    )
                    actions.add(
                        DialogText(
                            context.getString(R.string.Me_name),
                            context.getString(R.string.marry8)
                        )
                    )
                    actions.add(ShowImage(name = Constants.S_GREEN_SURPRISED_IMAGE_FILENAME))
                    actions.add(
                        DialogText(
                            context.getString(R.string.Sylvie_name),
                            context.getString(R.string.marry9)
                        )
                    )
                    actions.add(
                        DialogText(
                            context.getString(R.string.Me_name),
                            context.getString(R.string.marry10)
                        )
                    )
                    actions.add(ShowImage(name = Constants.S_BLUE_SMILE_IMAGE_FILENAME))
                    actions.add(
                        DialogText(
                            context.getString(R.string.Sylvie_name),
                            context.getString(R.string.marry11)
                        )
                    )
                    actions.add(
                        DialogText(
                            context.getString(R.string.Sylvie_name),
                            context.getString(R.string.marry12)
                        )
                    )
                    actions.add(
                        DialogText(
                            context.getString(R.string.Me_name),
                            context.getString(R.string.marry13)
                        )
                    )
                    actions.add(ShowImage(name = Constants.S_BLUE_GIGGLE_IMAGE_FILENAME))
                    actions.add(
                        DialogText(
                            context.getString(R.string.Sylvie_name),
                            context.getString(R.string.marry14)
                        )
                    )
                    actions.add(ShowImage(name = Constants.S_BLUE_NORMAL_IMAGE_FILENAME))
                    actions.add(
                        DialogText(
                            context.getString(R.string.Sylvie_name),
                            context.getString(R.string.marry15)
                        )
                    )
                    actions.add(
                        DialogText(
                            context.getString(R.string.Me_name),
                            context.getString(R.string.marry16)
                        )
                    )
                    actions.add(
                        DialogText(
                            context.getString(R.string.Sylvie_name),
                            context.getString(R.string.marry17)
                        )
                    )
                    actions.add(
                        DialogText(
                            context.getString(R.string.Me_name),
                            context.getString(R.string.marry18)
                        )
                    )
                    actions.add(ShowImage(name = Constants.S_BLUE_GIGGLE_IMAGE_FILENAME))
                    actions.add(
                        DialogText(
                            context.getString(R.string.Sylvie_name),
                            context.getString(R.string.marry19)
                        )
                    )
                },
                Scene().apply {
                    scene_bg = Constants.BLACK_BG_FILENAME
                    actions.add(AuthorText(context.getString(R.string.marry20)))
                    actions.add(AuthorText(context.getString(R.string.marry21)))
                    actions.add(AuthorText(context.getString(R.string.marry22)))
                    actions.add(AuthorText(context.getString(R.string.marry23)))
                }
            )
        },
        context.getString(R.string.later_scene) to SceneSet().apply {
            actions = arrayListOf(
                Scene().apply {
                    scene_bg = Constants.UNI_BG_FILENAME
                    actions.add(AuthorText(context.getString(R.string.later1)))
                },
                Scene().apply {
                    scene_bg = Constants.BLACK_BG_FILENAME
                    actions.add(AuthorText(context.getString(R.string.later2)))
                    actions.add(AuthorText(context.getString(R.string.later3)))
                    actions.add(AuthorText(context.getString(R.string.later4)))
                    actions.add(AuthorText(context.getString(R.string.bad_end)))
                }
            )
        }
    )
}

