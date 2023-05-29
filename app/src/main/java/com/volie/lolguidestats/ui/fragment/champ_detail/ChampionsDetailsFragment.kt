package com.volie.lolguidestats.ui.fragment.champ_detail

import android.animation.ObjectAnimator
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.tabs.TabLayout
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
    private val fragments = ArrayList<Fragment>()
    private val title = ArrayList<String>()

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
            .load("${CHAMPION_URL}${champ.name}_0.jpg")
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

        mBinding.tvBlurb.text = champ.blurb

        updateCircularProgressWithAnimator(mBinding.circularPbAttack, champ.info!!.attack)
        updateCircularProgressWithAnimator(mBinding.circularPbDefense, champ.info.defense)
        updateCircularProgressWithAnimator(mBinding.circularPbMagic, champ.info.magic)
        updateCircularProgressWithAnimator(mBinding.circularPbDifficulty, champ.info.difficulty)


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
                    val result = it.data!!.data.get(mArgs.details.id)

                    with(fragments) {
                        add(
                            SkillPageFragment(
                                result?.spells?.get(0)!!.name,
                                result.spells.get(0).description
                            )
                        )

                        add(
                            SkillPageFragment(
                                result.spells.get(1).name,
                                result.spells.get(1).description
                            )
                        )

                        add(
                            SkillPageFragment(
                                result.spells.get(2).name,
                                result.spells.get(2).description
                            )
                        )

                        add(
                            SkillPageFragment(
                                result.spells.get(3).name,
                                result.spells.get(3).description
                            )
                        )
                    }

                    val adapter = SkillViewPagerAdapter(
                        fragments,
                        requireActivity()
                    )

                    mBinding.viewPagerSkills.adapter = adapter

                    TabLayoutMediator(
                        mBinding.tabLayoutSkills,
                        mBinding.viewPagerSkills
                    ) { _, position ->
                        when (position) {
                            0 -> loadSpellsImage(
                                "${CHAMPION_IMAGE_URL}/spell/${result?.spells?.get(0)?.id}.png", 0
                            )

                            1 -> loadSpellsImage(
                                "${CHAMPION_IMAGE_URL}/spell/${result?.spells?.get(1)?.id}.png", 1
                            )

                            2 -> loadSpellsImage(
                                "${CHAMPION_IMAGE_URL}/spell/${result?.spells?.get(2)?.id}.png", 2
                            )

                            3 -> loadSpellsImage(
                                "${CHAMPION_IMAGE_URL}/spell/${result?.spells?.get(3)?.id}.png", 3
                            )
                        }
                    }.attach()
                }

                Status.LOADING -> {}
                Status.ERROR -> {}
            }
        }
    }

    private fun loadSpellsImage(url: String, index: Int) {
        Glide.with(requireContext())
            .load(url)
            .override(64, 64)
            .into(object : CustomViewTarget<TabLayout, Drawable>(mBinding.tabLayoutSkills) {
                override fun onLoadFailed(errorDrawable: Drawable?) {
                    Log.e("TAG", "onLoadFailed: ")
                }

                override fun onResourceCleared(placeholder: Drawable?) {
                    Log.e("TAG", "onResourceCleared: ")
                }

                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    val tab = mBinding.tabLayoutSkills.getTabAt(index)
                    tab?.icon = resource
                }

            })
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
            duration = 1500
            interpolator = LinearInterpolator()
            start()
        }
    }

    private fun updateCircularProgressWithAnimator(
        progressIndicator: CircularProgressIndicator,
        endValue: Int,
    ) {
        progressIndicator.progress = endValue

        val animator = ObjectAnimator.ofInt(progressIndicator, "progress", 0, endValue)
        with(animator) {
            duration = 3000
            start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}