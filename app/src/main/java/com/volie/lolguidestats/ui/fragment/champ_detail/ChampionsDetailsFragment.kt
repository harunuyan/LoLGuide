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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.tabs.TabLayoutMediator
import com.volie.lolguidestats.R
import com.volie.lolguidestats.databinding.FragmentChampionsDetailsBinding
import com.volie.lolguidestats.helper.Constant.BASE_URL
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



        showDetails()
        mViewModel.getChampionDetails("${mArgs.details.name}")
        observeLiveData()
    }

    private fun showDetails() {
        val champ = mArgs.details

        mBinding.tvChampName.text = champ.name

        Glide.with(requireContext())
            .load("${CHAMPION_URL}${champ.id}_0.jpg")
            .into(mBinding.ivChampImage)

        mBinding.tvChampTitle.text = champ.title

        with(mBinding) {

            updateLinearProgressWithAnimator(
                pbHp,
                tvHpCount,
                champ.stats!!.hp.toInt()
            )
            if (champ.stats.mp.toInt() == 0) {
                pbMp.visibility = View.GONE
                tvMp.visibility = View.GONE
                tvMpCount.visibility = View.GONE
            } else {
                updateLinearProgressWithAnimator(
                    pbMp,
                    tvMpCount,
                    champ.stats.mp.toInt()
                )
            }

            updateLinearProgressWithAnimator(
                pbArmor,
                tvArmorCount,
                champ.stats.armor.toInt()
            )
            updateLinearProgressWithAnimator(
                pbSpellBlock,
                tvSpellBlockCount,
                champ.stats.spellblock.toInt()
            )
            updateLinearProgressWithAnimator(
                pbAttackDamage,
                tvAttackDamageCount,
                champ.stats.attackdamage.toInt()
            )
            updateLinearProgressWithAnimator(
                pbHpRegen,
                tvHpRegenCount,
                champ.stats.hpregen.toInt()
            )
            if (champ.stats.mpregen.toInt() == 0) {
                tvMpRegen.visibility = View.GONE
                pbMpRegen.visibility = View.GONE
                tvMpRegenCount.visibility = View.GONE
            } else {
                updateLinearProgressWithAnimator(
                    pbMpRegen,
                    tvMpRegenCount,
                    champ.stats.mpregen.toInt()
                )
            }

            updateLinearProgressWithAnimator(
                pbAttackRange,
                tvAttackRangeCount,
                champ.stats.attackrange.toInt()
            )
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
    }

    private fun observeLiveData() {
        mViewModel.champions.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    val result = it.data!!.data[mArgs.details.id]!!

                    with(mBinding) {

                        tvBlurb.text = result.lore

                        if (!result.allytips.isNullOrEmpty()) {
                            for (i in result.allytips.indices) {
                                tvTipsContent.append("${result.allytips[i]}\n\n")
                            }
                        } else {
                            tvTips.visibility = View.GONE
                            tvTipsContent.visibility = View.GONE
                            viewTips.visibility = View.GONE
                            viewTipsEnd.visibility = View.GONE
                        }

                        if (!result.enemytips.isNullOrEmpty()) {
                            for (i in result.enemytips.indices) {
                                tvEnemyTipsContent.append("${result.enemytips[i]}\n\n")
                            }
                        } else {
                            tvEnemyTips.visibility = View.GONE
                            tvEnemyTipsContent.visibility = View.GONE
                            viewEnemyTips.visibility = View.GONE
                            viewEnemyTipsEnd.visibility = View.GONE
                        }

                        with(skillFragment) {
                            add(
                                SkillPageFragment(
                                    result.passive?.name!!,
                                    result.passive.description!!,
                                    "${BASE_URL}img/passive/${result.passive.image?.full}"
                                )
                            )

                            add(
                                SkillPageFragment(
                                    result.spells?.get(0)!!.name,
                                    result.spells[0].description,
                                    "${BASE_URL}img/spell/${result.spells[0].image.full}"
                                )
                            )

                            add(
                                SkillPageFragment(
                                    result.spells[1].name,
                                    result.spells[1].description,
                                    "${BASE_URL}img/spell/${result.spells[1].image.full}"
                                )
                            )

                            add(
                                SkillPageFragment(
                                    result.spells[2].name,
                                    result.spells[2].description,
                                    "${BASE_URL}img/spell/${result.spells[2].image.full}"
                                )
                            )

                            add(
                                SkillPageFragment(
                                    result.spells[3].name,
                                    result.spells[3].description,
                                    "${BASE_URL}img/spell/${result.spells[3].image.full}"
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

                        viewPagerSkins.adapter = skinVPAdapter

                        val skillVPAdapter = SkillViewPagerAdapter(
                            skillFragment,
                            requireActivity()
                        )

                        skinTabLayoutMediator = TabLayoutMediator(
                            tabLayoutSkins,
                            viewPagerSkins
                        ) { tab, position ->
                            if (position < result.skins.size) {
                                tab.text = result.skins[position].name
                            }
                        }

                        skinTabLayoutMediator.attach()

                        viewPagerSkills.adapter = skillVPAdapter

                        skillTabLayoutMediator = TabLayoutMediator(
                            tabLayoutSkills,
                            viewPagerSkills
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