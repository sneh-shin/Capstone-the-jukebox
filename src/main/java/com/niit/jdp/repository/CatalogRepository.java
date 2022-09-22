/*
 *Author Name: Sneha Shinde
 *Date: 9/21/2022
 * Created With: IntelliJ IDEA Community Edition
 */
package com.niit.jdp.repository;

import com.niit.jdp.model.Song;
import com.niit.jdp.service.MusicPlayerService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CatalogRepository {
    Scanner scanner = new Scanner(System.in);
    SongRepository songRepository = new SongRepository();
    PlaylistRepository playlistRepository = new PlaylistRepository();
    int choice;
    MusicPlayerService musicPlayerService = new MusicPlayerService();
    public void displayCatalog(Connection connection) throws SQLException {
        do {
            System.out.println("==================================================================");
            System.out.println("                   Outside the Jukebox                            ");
            System.out.println("==================================================================");
            System.out.println("1.view all songs");
            System.out.println("2.view songs by artist");
            System.out.println("3.view songs by genre");
            System.out.println("4.view playlist");
            System.out.println("5.enter 0 to exit");
            choice = scanner.nextInt();
            if (choice == 1) {
                List<Song> songList = songRepository.getAll(connection);
                Collections.sort(songList, (o1, o2) -> o1.getSongName().compareTo(o2.getSongName()));
                songList.forEach(p -> {
                    System.out.println(p.getSongName());
                });
                choice = scanner.nextInt();
                String songPath = songList.get(choice - 1).getFilePath();
                musicPlayerService.play(songPath);
            } else if (choice == 2) {
                List<String> allArtistFromDatabase = songRepository.getAllArtistFromDatabase(connection);
                Collections.sort(allArtistFromDatabase);
                System.out.println(allArtistFromDatabase);
                choice = scanner.nextInt();
                String artistName = allArtistFromDatabase.get(choice - 1);
                List<Song> songList = songRepository.getByArtistName(connection, artistName);
                songList.forEach(song -> {
                    System.out.println(song.getSongName());
                });
                choice = scanner.nextInt();
                musicPlayerService.play(songList.get(choice - 1).getFilePath());
            } else if (choice == 3) {
                List<String> genreFromDatabase = songRepository.getGenreFromDatabase(connection);
                Collections.sort(genreFromDatabase);
                for (String genre : genreFromDatabase) {
                    System.out.println(genre);
                }
                choice = scanner.nextInt();
                String genreName = genreFromDatabase.get(choice - 1);
                List<Song> songList = songRepository.getByGenreName(connection, genreName);
                for (Song song : songList) {
                    System.out.println(song.getSongName());
                }
                choice = scanner.nextInt();
                musicPlayerService.play(songList.get(choice - 1).getFilePath());
            } else if (choice == 4) {

            }


