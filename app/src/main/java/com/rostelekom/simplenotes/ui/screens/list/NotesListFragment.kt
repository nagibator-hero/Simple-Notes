package com.rostelekom.simplenotes.ui.screens.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.rostelekom.simplenotes.R
import com.rostelekom.simplenotes.databinding.FragmentNotesListBinding
import com.rostelekom.simplenotes.ui.adapters.NotesAdapter
import com.rostelekom.simplenotes.ui.models.Notes

class NotesListFragment : Fragment() {

    private lateinit var binding: FragmentNotesListBinding
    private val viewModel: NotesListViewModel by lazy { ViewModelProvider(this).get(NotesListViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNotesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        /*val testNotesList: ArrayList<Notes> = arrayListOf(
            Notes("Любимое занятие", "Порой я очень люблю анально ублажать себя, и это всё что стоит знатьПорой я очень люблю анально ублажать себя, и это всё что стоит знатьПорой я очень люблю анально ублажать себя, и это всё что стоит знать"),
            Notes("Люблю себя", "Лорем ипсум дорор сит амет, аверс катаверс")
        )
        rvAdapter.changeList(testNotesList)*/

    }

    private fun init() {
        viewModel.initDatabase()

        val rv = binding.notesRv
        val rvAdapter = NotesAdapter()
        rv.apply {
            adapter = rvAdapter
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
        }

        viewModel.getAllNotes().observe(viewLifecycleOwner) { listArray ->
            listArray.asReversed()
            rvAdapter.changeList(listArray)
        }
    }

}