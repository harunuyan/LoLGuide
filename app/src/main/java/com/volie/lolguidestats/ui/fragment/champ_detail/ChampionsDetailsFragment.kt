package com.volie.lolguidestats.ui.fragment.champ_detail

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.tabs.TabLayoutMediator
import com.volie.lolguidestats.R
import com.volie.lolguidestats.databinding.FragmentChampionsDetailsBinding
import com.volie.lolguidestats.helper.Constant.CHAMPION_IMAGE_URL
import com.volie.lolguidestats.helper.Constant.CHAMPION_URL
import com.volie.lolguidestats.helper.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChampionsDetailsFragment : Fragment() {
    private var _mBinding: FragmentChampionsDetailsBinding? = null
    private val mBinding get() = _mBinding!!
    private val mViewModel: ChampionsDetailsViewModel by viewModels()
    private val mArgs: ChampionsDetailsFragmentArgs by navArgs()
    private val skillFragment = ArrayList<Fragment>()
    private val skinFragment = ArrayList<Fragment>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentChampionsDetailsBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()

        showDetails()
        mViewModel.getChampionDetails("${mArgs.details.name}")
    }

    private fun showDetails() {
        val champ = mArgs.details

        mBinding.tvChampName.text = champ.name

        Glide.with(requireContext())
            .load("${CHAMPION_URL}${champ.id}_0.jpg")
            .into(mBinding.ivChampImage)

        mBinding.tvChampTitle.text = champ.title

        updateLinearProgressWithAnimator(
            mBinding.pbHp,
            mBinding.tvHpCount,
            champ.stats!!.hp.toInt()
        )
        if (champ.stats.mp.toInt() == 0) {
            mBinding.pbMp.visibility = View.GONE
            mBinding.tvMp.visibility = View.GONE
            mBinding.tvMpCount.visibility = View.GONE
        } else {
            updateLinearProgressWithAnimator(
                mBinding.pbMp,
                mBinding.tvMpCount,
                champ.stats.mp.toInt()
            )
        }

        updateLinearProgressWithAnimator(
            mBinding.pbArmor,
            mBinding.tvArmorCount,
            champ.stats.armor.toInt()
        )
        updateLinearProgressWithAnimator(
            mBinding.pbSpellBlock,
            mBinding.tvSpellBlockCount,
            champ.stats.spellblock.toInt()
        )
        updateLinearProgressWithAnimator(
            mBinding.pbAttackDamage,
            mBinding.tvAttackDamageCount,
            champ.stats.attackdamage.toInt()
        )
        updateLinearProgressWithAnimator(
            mBinding.pbHpRegen,
            mBinding.tvHpRegenCount,
            champ.stats.hpregen.toInt()
        )

        if (champ.stats.mpregen.toInt() == 0) {
            mBinding.tvMpRegen.visibility = View.GONE
            mBinding.pbMpRegen.visibility = View.GONE
            mBinding.tvMpRegenCount.visibility = View.GONE
        } else {
            updateLinearProgressWithAnimator(
                mBinding.pbMpRegen,
                mBinding.tvMpRegenCount,
                champ.stats.mpregen.toInt()
            )
        }

        updateLinearProgressWithAnimator(
            mBinding.pbAttackRange,
            mBinding.tvAttackRangeCount,
            champ.stats.attackrange.toInt()
        )

        mBinding.circularPbAttack.progress = champ.info!!.attack
        mBinding.circularPbDefense.progress = champ.info.defense
        mBinding.circularPbMagic.progress = champ.info.magic
        mBinding.circularPbDifficulty.progress = champ.info.difficulty


        when (champ.tags?.get(0)) {
            "Fighter" -> {
                mBinding.ivChampClassFirst.setImageResource(R.drawable.ic_fighter)
            }

            "Tank" -> {
                mBinding.ivChampClassFirst.setImageResource(R.drawable.ic_tank)
            }

            "Mage" -> {
                mBinding.ivChampClassFirst.setImageResource(R.drawable.ic_mage)
            }

            "Assassin" -> {
                mBinding.ivChampClassFirst.setImageResource(R.drawable.ic_assassin)
            }

            "Marksman" -> {
                mBinding.ivChampClassFirst.setImageResource(R.drawable.ic_marksman)
            }

            "Support" -> {
                mBinding.ivChampClassFirst.setImageResource(R.drawable.ic_tank)
            }

            else -> {
                mBinding.ivChampClassFirst.visibility = View.INVISIBLE
            }
        }

        if (champ.tags?.size == 1) {
            mBinding.ivChampClassSecond.visibility = View.GONE
        } else {
            when (champ.tags?.get(1)) {
                "Fighter" -> {
                    mBinding.ivChampClassSecond.setImageResource(R.drawable.ic_fighter)
                }

                "Tank" -> {
                    mBinding.ivChampClassSecond.setImageResource(R.drawable.ic_tank)
                }

                "Mage" -> {
                    mBinding.ivChampClassSecond.setImageResource(R.drawable.ic_mage)
                }

                "Assassin" -> {
                    mBinding.ivChampClassSecond.setImageResource(R.drawable.ic_assassin)
                }

                "Marksman" -> {
                    mBinding.ivChampClassSecond.setImageResource(R.drawable.ic_marksman)
                }

                else -> {
                    mBinding.ivChampClassSecond.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun observeLiveData() {
        mViewModel.champions.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    val result = it.data!!.data[mArgs.details.id]!!

                    mBinding.tvBlurb.text = result.lore

                    if (!result.allytips.isNullOrEmpty()) {
                        for (i in result.allytips.indices) {
                            mBinding.tvTipsContent.append("${result.allytips[i]}\n\n")
                        }
                    } else {
                        mBinding.tvTips.visibility = View.GONE
                        mBinding.tvTipsContent.visibility = View.GONE
                        mBinding.viewTips.visibility = View.GONE
                        mBinding.viewTipsEnd.visibility = View.GONE
                    }

                    if (!result.enemytips.isNullOrEmpty()) {
                        for (i in result.enemytips.indices) {
                            mBinding.tvEnemyTipsContent.append("${result.enemytips[i]}\n\n")
                        }
                    } else {
                        mBinding.tvEnemyTips.visibility = View.GONE
                        mBinding.tvEnemyTipsContent.visibility = View.GONE
                        mBinding.viewEnemyTips.visibility = View.GONE
                        mBinding.viewEnemyTipsEnd.visibility = View.GONE
                    }

                    with(skillFragment) {
                        add(
                            SkillPageFragment(
                                result.passive?.name!!,
                                result.passive.description!!,
                                "${CHAMPION_IMAGE_URL}passive/${result.passive.image?.full}"
                            )
                        )

                        add(
                            SkillPageFragment(
                                result.spells?.get(0)!!.name,
                                result.spells.get(0).description,
                                "${CHAMPION_IMAGE_URL}spell/${result.spells.get(0).image.full}"
                            )
                        )

                        add(
                            SkillPageFragment(
                                result.spells.get(1).name,
                                result.spells.get(1).description,
                                "${CHAMPION_IMAGE_URL}spell/${result.spells.get(1).image.full}"
                            )
                        )

                        add(
                            SkillPageFragment(
                                result.spells.get(2).name,
                                result.spells.get(2).description,
                                "${CHAMPION_IMAGE_URL}spell/${result.spells.get(2).image.full}"
                            )
                        )

                        add(
                            SkillPageFragment(
                                result.spells.get(3).name,
                                result.spells.get(3).description,
                                "${CHAMPION_IMAGE_URL}spell/${result.spells.get(3).image.full}"
                            )
                        )
                    }

                    for (i in 0 until result.skins!!.size) {
                        skinFragment.add(
                            SkinPageFragment("${CHAMPION_URL}${result.id}_$i.jpg")
                        )
                    }

                    val skinVPAdapter = SkinViewPagerAdapter(
                        skinFragment,
                        requireActivity()
                    )

                    mBinding.viewPagerSkins.isUserInputEnabled = false
                    mBinding.viewPagerSkins.offscreenPageLimit = result.skins.size
                    mBinding.viewPagerSkins.adapter = skinVPAdapter


                    val skillVPAdapter = SkillViewPagerAdapter(
                        skillFragment,
                        requireActivity()
                    )

                    TabLayoutMediator(
                        mBinding.tabLayoutSkins,
                        mBinding.viewPagerSkins
                    ) { tab, position ->
                        tab.text = result.skins[position].name
                    }.attach()

                    mBinding.viewPagerSkills.adapter = skillVPAdapter

                    TabLayoutMediator(
                        mBinding.tabLayoutSkills,
                        mBinding.viewPagerSkills
                    ) { tab, position ->
                        when (position) {
                            0 -> tab.text = "Passive"
                            1 -> tab.text = "Q"
                            2 -> tab.text = "W"
                            3 -> tab.text = "E"
                            4 -> tab.text = "R"
                        }
                    }.attach()
                }

                Status.LOADING -> {}
                Status.ERROR -> {}
            }
        }
    }

    private fun updateLinearProgressWithAnimator(
        progressIndicator: LinearProgressIndicator,
        text: TextView,
        endValue: Int
    ) {
        progressIndicator.progress = endValue
        text.text = endValue.toString()

        val animator = ObjectAnimator.ofInt(progressIndicator, "progress", 0, endValue)
        with(animator) {
            duration = 1000
            interpolator = LinearInterpolator()
            start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}