package com.volie.lolguidestats.ui.fragment.champ_detail

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.tabs.TabLayoutMediator
import com.volie.lolguidestats.R
import com.volie.lolguidestats.databinding.FragmentChampionsDetailsBinding
import com.volie.lolguidestats.helper.Constant.BASE_URL
import com.volie.lolguidestats.helper.Constant.CHAMPION_URL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChampionsDetailsFragment : Fragment() {
    private var _mBinding: FragmentChampionsDetailsBinding? = null
    private val mBinding get() = _mBinding!!
    private val mArgs: ChampionsDetailsFragmentArgs by navArgs()
    private val skillFragment = ArrayList<Fragment>()
    private val skinFragment = ArrayList<Fragment>()
    private lateinit var skillTabLayoutMediator: TabLayoutMediator
    private lateinit var skinTabLayoutMediator: TabLayoutMediator

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

        with(mBinding) {
            flBack.setOnClickListener {
                findNavController().navigateUp()
            }

            tvChampName.setOnClickListener {
                findNavController().navigateUp()
            }
        }

        setupViewPagerSkill()
        setupViewPagerSkin()

        showDetails()
    }

    private fun showDetails() {
        val champ = mArgs.details

        mBinding.tvChampName.text = champ.name

        Glide.with(requireContext())
            .load("${CHAMPION_URL}${champ.id}_0.jpg")
            .into(mBinding.ivChampImage)

        mBinding.tvChampTitle.text = champ.title

        with(mBinding) {

            with(champ.stats) {

                this?.let {
                    updateLinearProgressWithAnimator(
                        pbHp,
                        tvHpCount,
                        hp.toInt()
                    )
                    if (mp.toInt() == 0) {
                        pbMp.visibility = View.GONE
                        tvMp.visibility = View.GONE
                        tvMpCount.visibility = View.GONE
                    } else {
                        updateLinearProgressWithAnimator(
                            pbMp,
                            tvMpCount,
                            mp.toInt()
                        )
                    }

                    updateLinearProgressWithAnimator(
                        pbArmor,
                        tvArmorCount,
                        armor.toInt()
                    )
                    updateLinearProgressWithAnimator(
                        pbSpellBlock,
                        tvSpellBlockCount,
                        spellblock.toInt()
                    )
                    updateLinearProgressWithAnimator(
                        pbAttackDamage,
                        tvAttackDamageCount,
                        attackdamage.toInt()
                    )
                    updateLinearProgressWithAnimator(
                        pbHpRegen,
                        tvHpRegenCount,
                        hpregen.toInt()
                    )
                    if (mpregen.toInt() == 0) {
                        tvMpRegen.visibility = View.GONE
                        pbMpRegen.visibility = View.GONE
                        tvMpRegenCount.visibility = View.GONE
                    } else {
                        updateLinearProgressWithAnimator(
                            pbMpRegen,
                            tvMpRegenCount,
                            mpregen.toInt()
                        )
                    }

                    updateLinearProgressWithAnimator(
                        pbAttackRange,
                        tvAttackRangeCount,
                        attackrange.toInt()
                    )
                }

            }
        }

        val champInfo = champ.info
        with(mBinding) {
            if (champInfo != null) {
                circularPbAttack.progress = champInfo.attack
                circularPbDefense.progress = champInfo.defense
                circularPbMagic.progress = champInfo.magic
                circularPbDifficulty.progress = champInfo.difficulty
            } else {
                circularPbAttack.visibility = View.GONE
                circularPbDefense.visibility = View.GONE
                circularPbMagic.visibility = View.GONE
                circularPbDifficulty.visibility = View.GONE
            }
        }


        with(mBinding.ivChampClassFirst) {
            when (champ.tags?.get(0)) {

                "Fighter" -> {
                    setImageResource(R.drawable.ic_fighter)
                }

                "Tank" -> {
                    setImageResource(R.drawable.ic_tank)
                }

                "Mage" -> {
                    setImageResource(R.drawable.ic_mage)
                }

                "Assassin" -> {
                    setImageResource(R.drawable.ic_assassin)
                }

                "Marksman" -> {
                    setImageResource(R.drawable.ic_marksman)
                }

                "Support" -> {
                    setImageResource(R.drawable.ic_tank)
                }

                "Slayer" -> {
                    setImageResource(R.drawable.ic_slayer)
                }

                else -> {
                    visibility = View.INVISIBLE
                }
            }
        }

        with(mBinding.ivChampClassSecond) {
            if (champ.tags?.size == 1) {
                visibility = View.GONE
            } else {
                when (champ.tags?.get(1)) {
                    "Fighter" -> {
                        setImageResource(R.drawable.ic_fighter)
                    }

                    "Tank" -> {
                        setImageResource(R.drawable.ic_tank)
                    }

                    "Mage" -> {
                        setImageResource(R.drawable.ic_mage)
                    }

                    "Assassin" -> {
                        setImageResource(R.drawable.ic_assassin)
                    }

                    "Marksman" -> {
                        setImageResource(R.drawable.ic_marksman)
                    }

                    "Slayer" -> {
                        setImageResource(R.drawable.ic_slayer)
                    }

                    else -> {
                        visibility = View.INVISIBLE
                    }
                }
            }
        }

        with(mBinding) {

            with(mArgs.details) {

                tvBlurb.text = lore

                if (!allytips.isNullOrEmpty()) {
                    for (i in allytips.indices) {
                        tvTipsContent.append("${allytips[i]}\n\n")
                    }
                } else {
                    tvTips.visibility = View.GONE
                    tvTipsContent.visibility = View.GONE
                    viewTips.visibility = View.GONE
                    viewTipsEnd.visibility = View.GONE
                }

                if (!enemytips.isNullOrEmpty()) {
                    for (i in enemytips.indices) {
                        tvEnemyTipsContent.append("${enemytips[i]}\n\n")
                    }
                } else {
                    tvEnemyTips.visibility = View.GONE
                    tvEnemyTipsContent.visibility = View.GONE
                    viewEnemyTips.visibility = View.GONE
                    viewEnemyTipsEnd.visibility = View.GONE
                }
            }


        }
    }

    private fun setupViewPagerSkill() {
        with(skillFragment) {
            with(mArgs.details) {
                add(
                    SkillPageFragment(
                        passive?.name!!,
                        passive.description!!,
                        "${BASE_URL}img/passive/${passive.image?.full}"
                    )
                )

                add(
                    SkillPageFragment(
                        spells?.get(0)!!.name,
                        spells[0].description,
                        "${BASE_URL}img/spell/${spells[0].image.full}"
                    )
                )

                add(
                    SkillPageFragment(
                        spells[1].name,
                        spells[1].description,
                        "${BASE_URL}img/spell/${spells[1].image.full}"
                    )
                )

                add(
                    SkillPageFragment(
                        spells[2].name,
                        spells[2].description,
                        "${BASE_URL}img/spell/${spells[2].image.full}"
                    )
                )

                add(
                    SkillPageFragment(
                        spells[3].name,
                        spells[3].description,
                        "${BASE_URL}img/spell/${spells[3].image.full}"
                    )
                )
            }
        }

        val skillVPAdapter = SkillViewPagerAdapter(
            skillFragment,
            requireActivity()
        )
        mBinding.viewPagerSkills.adapter = skillVPAdapter

        skillTabLayoutMediator = TabLayoutMediator(
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
        }

        skillTabLayoutMediator.attach()
    }

    private fun setupViewPagerSkin() {
        with(mArgs.details) {
            for (i in 0 until skins!!.size) {
                skinFragment.add(
                    SkinPageFragment("${CHAMPION_URL}${id}_$i.jpg")
                )
            }
            val skinVPAdapter = SkinViewPagerAdapter(
                skinFragment,
                requireActivity()
            )

            mBinding.viewPagerSkins.adapter = skinVPAdapter

            skinTabLayoutMediator = TabLayoutMediator(
                mBinding.tabLayoutSkins,
                mBinding.viewPagerSkins
            ) { tab, position ->
                if (position < skins.size) {
                    tab.text = skins[position].name
                }
            }

            skinTabLayoutMediator.attach()
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

    override fun onPause() {
        skinFragment.clear()
        skinTabLayoutMediator.detach()
        skillFragment.clear()
        skillTabLayoutMediator.detach()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}