import React from "react";
import { StyleSheet, TouchableOpacity, Image, View } from "react-native";
import TextComponent from "./TextComponents";
import RatingComponent from "./RatingComponent";
import Scale from "@/constants/Scale";
import GenreComponent from "./GenreComponent";

interface Notification {
    imageUrl: string
    message: string,
    title: string,
    rating: number,
    genre_list: string[]
};

export interface NotificationData {
    notifications: Notification[]
};

export default function NotificationComponent({imageUrl, message, title, rating, genre_list} : Notification) {
    return (
        <TouchableOpacity style={styles.contentItem}>
            <Image
                source={{ uri: imageUrl }}
                style={styles.thumbnail}
            />
            <View style={styles.textContent}>
                <TextComponent
                    text={message}
                    numberOfLine={4}
                    size={12}
                    color="#FFFFFF"
                />
                <View style={styles.title}>
                    <TextComponent
                        text={title}
                        numberOfLine={2}
                        size={18}
                        bold
                        color="#FFFFFF"
                        style={styles.titleText}
                    />
                    {/* OTT platform will come here  */}
                </View>
                <View style={styles.rating_n_genre}>
                    {/* Rating and genre will come here */}
                    <View style={styles.rating}>
                        <RatingComponent rating = {rating}/>
                    </View>
                    <GenreComponent genre_list={genre_list} />
                </View>
            </View>
        </TouchableOpacity>
    )
};

const styles = StyleSheet.create({
    contentItem: {
        flexDirection: 'row',
        backgroundColor: "#191919",
        borderRadius: 13.43,
        marginBottom: 10,
    },
    thumbnail: {
        width: 81,
        aspectRatio: 2 / 3,
        borderRadius: 14,
    },
    textContent: {
        paddingLeft: 15,
        flexDirection: 'column',
        alignItems: 'flex-start',
        paddingVertical: 10,
    },
    title: {
        paddingVertical: 20,
        flexDirection: 'row'
    },
    titleText:{
        fontWeight: 'bold',
    },
    rating_n_genre: {
        flexDirection: 'row',
    },
    rating: {
        paddingRight: 10,
    }
});