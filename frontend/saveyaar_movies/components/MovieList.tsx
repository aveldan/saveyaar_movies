import React, { useEffect, useState } from "react";
import { FlatList, StyleSheet, View, Image, TouchableOpacity } from "react-native";
import { useRouter } from "expo-router";

import RatingComponent from "./RatingComponent";
import TextComponent from "./TextComponents";
import { BellIcon, HeartIcon } from "@/assets/svg";
import { TMDB_KEY, IMAGE_URL } from  '@env';

interface MovieType {
    id: number;
    poster_path: string;
    title: string;
    backdrop_path: string,
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
    featured: MovieType;
    movies: MovieListType[];
}

const MovieItem = ({ item, router, index }: MovieItemType) => {
    
    const [imageData, setImageData] = useState<string | null>(null);

    useEffect(() => {
        const fetchImage = async () => {
            try {
                const response = await fetch(IMAGE_URL+item.poster_path, {
                  headers: {
                    Authorization: `Bearer ${TMDB_KEY}`,
                  },
                });
                const blob = await response.blob();
                const reader = new FileReader();

                reader.onloadend = () => {
                    if (typeof reader.result === 'string') {
                        setImageData(reader.result);
                    } else {
                        console.error('Expected string from FileReader, got:', typeof reader.result);
                    }
                };
                
                reader.readAsDataURL(blob);
            } catch (error) {
                console.error('Error fetching image:', error);
            }
        }

        fetchImage();
    }, [item.poster_path]);

    return (
        <View style={styles.contentItem}>
            <TouchableOpacity
                onPress={() => router.push({
                    pathname: '/movie/[id]',
                    params: { id: item.id }
                })} 
            >
                {imageData && 
                    <Image
                        source={{ uri: imageData }}
                        style={styles.thumbnail}
                    />
                }

            </TouchableOpacity>
            <TextComponent
                text={item.title}
                size={12}
                style={styles.title}
            />
            <View style={styles.icons}>
                <RatingComponent rating={parseFloat((Math.random() * 9 + 1).toFixed(1))}/>
                <TouchableOpacity style={styles.bellIcon}>
                    <BellIcon />
                </TouchableOpacity>
                <TouchableOpacity style={styles.heartIcon}>
                    <HeartIcon />
                </TouchableOpacity>
            </View>
        </View>
    );
};

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
        marginBottom: 15,
    },
    sectionTitle: {
        color: '#fff',
        fontSize: 18,
        fontWeight: 'bold',
        marginLeft: 5,
        marginBottom: 10,
    },
    contentItem: {
        marginRight: 12,
        position: 'relative',
        flexDirection: 'column',
        width: 120,
        height: 240
    },
    thumbnail: {
        width: '100%',
        aspectRatio: 2 / 3,
        // width: Scale(104),
        // height: Scale(156),
        borderRadius: 14,
        marginBottom: 4,
    },
    title: {
        paddingHorizontal: 4,
        paddingTop: 4,
        paddingBottom: 7,
    },
    icons: {
        flexDirection: 'row',
    },
    bellIcon: {
        paddingLeft: 35,
    },
    heartIcon: {
        paddingLeft: 5,
    }
});