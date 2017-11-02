package com.eltonkola.crud.vaadin;
import com.eltonkola.crud.domain.Song;
import com.eltonkola.crud.service.SongServiceInterface;
import com.kbdunn.vaadin.addons.mediaelement.MediaElementPlayer;
import com.kbdunn.vaadin.addons.mediaelement.interfaces.PlaybackEndedListener;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.util.StringUtils;

import java.io.File;

@SpringUI(path = "/ui")
@Title("Test")
@Theme("valo")
public class VaadinUI extends UI {



    @Autowired
    SongServiceInterface mSongServiceInterface;




    @Autowired
    CustomerEditor editor;

    final Grid<Song> grid;

    final TextField filter;

    private final Button addNewBtn;
    MediaElementPlayer audioPlayer;


    @Autowired
    public VaadinUI() {
        this.grid = new Grid<>(Song.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New customer", FontAwesome.PLUS);
    }

    @Override
    protected void init(VaadinRequest request) {
        // build layout

        audioPlayer = new MediaElementPlayer();

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn, audioPlayer);
        VerticalLayout mainLayout = new VerticalLayout(actions, grid);//, editor
        setContent(mainLayout);

        grid.setHeight(300, Unit.PIXELS);
        grid.setColumns("id", "songtitle", "artistname");

        filter.setPlaceholder("Filter by last name");

        // Hook logic to components

        // Replace listing with filtered content when user changes filter
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> listCustomers(e.getValue()));

        // Connect selected Customer to editor or hide if none is selected
        grid.asSingleSelect().addValueChangeListener(e -> {
            //editor.editCustomer(e.getValue());

            playSong(e.getValue());
        });

        // Instantiate and edit new Customer the new button is clicked
        addNewBtn.addClickListener(e -> editor.editCustomer(new Song("", "", "")));
//
//        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(new CustomerEditor.ChangeHandler() {
            @Override
            public void onChange() {
                editor.setVisible(false);
                listCustomers(filter.getValue());
            }
        });

        // Initialize listing
        listCustomers(null);
    }

    private void playSong(Song value) {


        audioPlayer.setSource(new FileResource(new File(value.getUrlsong())));
//        audioPlayer.addPlaybackEndedListener(new PlaybackEndedListener() {
//            @Override
//            public void playbackEnded(MediaElementPlayer player) {
//                player.setSource(new FileResource(new File(value.getUrlsong())));
//                player.play();
//            }
//        });
        audioPlayer.play();

    }

    // tag::listCustomers[]
    void listCustomers(String filterText) {
        if (StringUtils.isEmpty(filterText) || filterText == null) {
            grid.setItems(mSongServiceInterface.getRandom100());
        }
        else {
            grid.setItems(mSongServiceInterface.kerko(filterText));
        }
    }

    // end::listCustomers[]




//
//    Grid<Song> mGrid;
//
//
//    @Override
//    protected void init(VaadinRequest request) {
//        mGrid = new Grid<>(Song.class);
//
//        mGrid.setItems(mSongServiceInterface.getRandom100());
//
//        setContent(mGrid);
//        //setContent(new Button("Click me", e -> Notification.show("Hello Spring+Vaadin user!")));
//    }
}