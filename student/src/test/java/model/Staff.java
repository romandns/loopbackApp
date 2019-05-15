package model;

public class Staff {
        private String firstName;
        private String lastName;
        private String staffPosition;
        private String starship;
        private String id;

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getStaffPosition() {
            return staffPosition;
        }

        public String getStarship() {
            return starship;
        }

        public String getId() {
            return id;
        }

        public Staff withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Staff withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Staff withStaffPosition(String staffPosition) {
            this.staffPosition = staffPosition;
            return this;
        }

        public Staff withStarship(String starship) {
            this.starship = starship;
            return this;
        }

        public Staff withId(String id) {
            this.id = id;
            return this;
        }

    }
