package com.volie.lolguidestats.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.volie.lolguidestats.R
import com.volie.lolguidestats.databinding.FragmentHomeBinding
import com.volie.lolguidestats.ui.adapter.BaseViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _mBinding: FragmentHomeBinding? = null
    private val mBinding get() = _mBinding!!
    private val fragment = ArrayList<Fragment>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showChampClasses()
    }

    private fun showChampClasses() {

        with(fragment) {
            add(
                ChampClassesFragment(
                    "Controller",
                    "Controllers assist their allies with potent utility and keep enemies at bay with crowd control. Weak when alone, supports are capable of massively amplifying their teammates' power to become the strongest class in group combat (or teamfights), supplying crucial utility or crowd control at clutch moments to save allies from death and enable takedowns on the enemy team.\n" +
                            "\n" +
                            "Supports typically start out by assisting the marksman in lane, as their own power is less dependent on items to function well, but over time their contribution expands as they lend aid to their entire team with both their spells and effective, yet affordable, items. Sub-classes of Controllers are enchanters and catchers."
                )
            )
            add(
                ChampClassesFragment(
                    "Fighter",
                    "Fighters (also known as Bruisers) are a diverse group of short-ranged combatants who excel at both dealing and surviving damage. With easy access to heavy, continuous damage (or DPS) and a host of innate defenses, fighters thrive in extended fights as they seek out enemies to take down, but their limited range puts them at constant risk of being kept at bay (or kited) by their opponents via crowd control, range and mobility.\n" +
                            "\n" +
                            "Fighters tend to have an advantage against assassins, whose burst tends to fall short of killing them when unaided, as well as tanks, whose inferior damage allows fighters to eventually defeat them in duels, but often struggle against mages and marksmen, whose superior reach allows them to kite approaching fighters. Sub-classes of the Fighters are juggernauts, and divers."
                )
            )
            add(
                ChampClassesFragment(
                    "Mage",
                    "Mages are champions who typically possess great reach, ability-based area of effect damage and crowd control, and who use all of these strengths in tandem with each other to trap and destroy enemies from a distance. Specializing in magic damage, often burst damage, and therefore investing heavily in items that allow them to cast stronger and faster spells, mages excel at chaining their abilities together in powerful combos in order to win fights, though their abilities also tend to be difficult to land and can be mitigated, if not avoided completely, by their targets if they react in time.\n" +
                            "\n" +
                            "Though mages tend to focus on killing prime targets in combat, they can also fall back to their innate crowd control and utility to manipulate key opponents, protecting their team from them or setting them up for a takedown, and in the right circumstances can damage and control multiple enemies at a time. In spite of the influence they exert, mages tend to be innately fragile, and fall quickly to direct strikes.\n" +
                            "\n" +
                            "In general, mages are capable of dealing well with marksmen, as their burst can kill them before they can return the same amount of damage, and fighters, as their crowd control tends to make them excellent kiters. However, they are easily shut down by assassins who can often bypass their reach and spells completely, and tanks, who can lock them down and soak up their abilities better than other classes. Sub-classes of Mages are burst, battle, and artillery Mages."
                )
            )
            add(
                ChampClassesFragment(
                    "Marksman",
                    "Marksmen are ranged champions whose power almost exclusively revolves around their basic attacks: using their reach to land massive continuous damage from a distance, marksmen are capable of taking down even the toughest of opponents when positioned behind the safety of their team, and excel at securing map objectives such as Dragon Dragon, Baron Nashor Baron Nashor and turrets.\n" +
                            "\n" +
                            "Marksmen are tremendously vulnerable to burst damage, due to their fragility, and tend to be exceptionally weak early in the game, requiring high amounts of gold, mostly via minion kills (or CS: Creep Score) to acquire powerful, but expensive, damage-focused items. Due to their potent reach and DPS, marksmen are particularly strong against more durable opponents, namely fighters and tanks, but fall quickly to the burst damage of assassins and mages."
                )
            )
            add(
                ChampClassesFragment(
                    "Slayer",
                    "Slayers are highly mobile champions who specialize in single target burst damage. What they generally lack in resilience, they more than make up for with their ability to quickly cover large distances, kill priority targets and retreat just as fast. Epitomizing a high risk, high reward playstyle, assassins are natural opportunists and prefer to strike when their targets are alone and vulnerable, rather than engage them in a direct assault, favoring damage-oriented item builds to capitalize on their offensive capabilities. They're particularly effective against softer (or squishy) targets, especially mages and marksmen, but often struggle against the heightened defenses of fighters and tanks. Subclasses of Slayers are assassins and skirmishers."
                )
            )
            add(
                ChampClassesFragment(
                    "Tank",
                    "Tanks are tough melee champions who sacrifice damage in exchange for powerful crowd control. While able to engage enemies in combat, a tank's purpose isn't usually to kill opponents; rather, tanks excel at disrupting enemies and diverting focus to themselves, allowing them to lock down specific targets (or several targets at once), as well as remove (or peel) threats from their allies.\n" +
                            "\n" +
                            "In addition to strong base defenses, tanks generally have a means of amplifying their tankiness even further with their abilities, and tend to fully invest in defensive items to maximize their resilience. However, tanks lack the tools to truly succeed in single combat, and their influence is limited by their low overall mobility, preventing them from constantly staying on top of their targets.\n" +
                            "\n" +
                            "As tanks can handle burst damage very well, they tend to succeed against assassins and most mages, but their vulnerability to continuous damage puts them at a disadvantage against fighters and marksmen. Subcategories of Tanks are vanguards, and wardens."
                )
            )
        }

        val champClassesViewPagerAdapter = BaseViewPagerAdapter(fragment, requireActivity())
        mBinding.viewPagerChampClasses.adapter = champClassesViewPagerAdapter

        TabLayoutMediator(
            mBinding.tabLayoutChampClasses,
            mBinding.viewPagerChampClasses
        ) { tab, position ->
            when (position) {
                0 -> tab.icon =
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_controller)

                1 -> tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_fighter)
                2 -> tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_mage)
                3 -> tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_marksman)
                4 -> tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_slayer)
                5 -> tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_tank)
            }
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}