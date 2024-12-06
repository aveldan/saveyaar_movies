import React from "react";
import { FlatList, StyleSheet, View, Image, TouchableOpacity } from "react-native";
import TextComponent from "./TextComponents";
import { useRouter } from "expo-router";
import Scale from "@/constants/Scale";
import RatingComponent from "./RatingComponent";

interface MovieType {
    id: string;
    imageUrl: string;
    title: string;
}

interface MovieListType {
    rowTitle: string;
    movies: MovieType[];
}

interface MovieItemType {
    item: MovieType;
    router: any;
    index: number;
}

export interface MoviesData {
    movies: MovieListType[];
}

const MovieItem = ({ item, router, index }: MovieItemType) => (
    <TouchableOpacity
        onPress={() => router.push({
            pathname: '/movie/[id]',
            params: { id: item.id }
        })}
        style={styles.contentItem}
    >
        <Image
            source={{ uri: item.imageUrl }}
            style={styles.thumbnail}
        />
        {/* <TextComponent
            text={item.title}
            size={12}
        /> */}
        <RatingComponent rating={8.8}/>
    </TouchableOpacity>
);

export default function MovieList({rowTitle, movies}: MovieListType) {

    const router = useRouter();
    
    return (
        <View style={styles.movieRow}>
            <TextComponent 
                text={rowTitle}
                style={styles.sectionTitle}
            />
            <FlatList
                horizontal
                data={movies}
                renderItem={({ item, index }) => (
                    <MovieItem
                        item={item}
                        router={router}
                        index={index}
                    />
                )}
            />
        </View>
    );
};

const styles = StyleSheet.create({
    movieRow: {
        marginBottom: 30,
    },
    sectionTitle: {
        color: '#fff',
        fontSize: 18,
        fontWeight: 'bold',
        marginLeft: 16,
        marginBottom: 10,
        marginTop: -8,
    },
    contentItem: {
        width: Scale(104),
        height: Scale(196),
        marginRight: 12,
        position: 'relative',
        flexDirection: 'column'
    },
    thumbnail: {
        // width: '100%',
        // aspectRatio: 2 / 3,
        width: Scale(104),
        height: Scale(156),
        borderRadius: 14,
        marginBottom: 4,
    },
});