import React from "react";
import { StyleSheet, View } from "react-native";
import TextComponent from "./TextComponents";
import { LineVerticalPurple } from "@/assets/svg";

interface GenreComponent {
    genre_list: string[]
};

export default function GenreComponent ({genre_list}: GenreComponent) {
    return (
        <View style={styles.container}>
            {genre_list.map((genre, index) => {
                const isNotLast = index !== genre_list.length-1;
                return (
                    <View key={genre} style={styles.item}>
                        <TextComponent
                            text={genre}
                            size={8}
                            color="#CCCCCC"
                            numberOfLine={1}
                            style={{paddingRight: 3}}
                        />
                        {isNotLast && 
                            <LineVerticalPurple />
                        }
                    </View>
                )
            })}
        </View>
    )
};

const styles = StyleSheet.create({
    container: {
        flexDirection: 'row',
    },
    item: {
        flexDirection: 'row',
        alignItems:'center',
        paddingRight: 3
    }
});