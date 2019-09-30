import React from "react";
import {
  TextField,
  InputAdornment,
  withStyles,
  IconButton
} from "@material-ui/core";
import classNames from "classnames";
import SearchIcon from "@material-ui/icons/Search";
import Button from "@material-ui/core/Button";
import Typography from "@material-ui/core/Typography";
import ClearIcon from "@material-ui/icons/Clear";
import AutoSuggestComponent from "./auto-suggest-component";

const styles = theme => ({
  root: {
    display: "flex",
    flexWrap: "wrap"
  },
  searchComponent: {
    width: "520px"
  },
  margin: {},
  textfield: {
    color: theme.palette.secondary.main,
    paddingLeft: theme.spacing.unit,
    width: "25%"
  },
  emptyTextfield: {
    color: "#9b9b9b"
  },
  clearSeperator: {
    height: "100%",
    borderRight: "1px solid #979797",
    marginRight: "5px"
  },
  iconButton: {
    width: "24px",
    height: "auto",
    "&:hover": {
      backgroundColor: "transparent"
    }
  },
  button: {
    margin: "0px 10px 0px 10px"
  },
  sort: {
    display: "flex",
    width: "75%",
    justifyContent: "flex-end"
  },
  search: {
    display: "flex"
  }
});

export class SearchAndSortComponent extends React.Component {
  state = {
    searchText: ""
  };
  handleChange = event => {
    this.setState({ [event.target.name]: event.target.value });
  };

  handleSearch = () => {
    this.props.handleSearch && this.props.handleSearch(this.state.searchText);
  };

  onClear = () => {
    this.setState({ searchText: "" });
    this.props.onClear && this.props.onClear();
  };

  renderSearchComponent = () => {
    const { isViewTask, classes } = this.props;
    const { searchText } = this.state;

    if (!isViewTask) {
      const inputProps = {
        disableUnderline: true,

        endAdornment: (
          <InputAdornment position="end">
            <div className={classes.clearSeperator} />
            <div>
              <IconButton
                id="device-search-icon"
                onClick={this.handleSearch}
                disabled={!searchText}
                className={classes.iconButton}
              >
                <SearchIcon
                  style={{ color: searchText ? "#0064d2" : "#9b9b9b" }}
                />
              </IconButton>
            </div>
            <div className={classes.clearSeperator} />

            <div>
              <IconButton
                id="device-clear-icon"
                onClick={this.onClear}
                disabled={!searchText}
                className={classes.iconButton}
              >
                <ClearIcon
                  style={{ color: searchText ? "#0064d2" : "#9b9b9b" }}
                />
              </IconButton>
            </div>
          </InputAdornment>
        )
      };
      return (
        <TextField
          id="searchText"
          color="secondary"
          name="searchText"
          className={classNames(classes.margin, classes.textfield)}
          value={searchText}
          onChange={this.handleChange}
          autoComplete="off"
          placeholder="Search"
          style={{ width: "25%" }}
          InputProps={inputProps}
        />
      );
    } else {
      return (
        <AutoSuggestComponent
          suggestions={this.props.fetchProjectSuggestions()}
          placeholder={"Search and Select Project"}
          onSuggestionSelected={this.props.onProjectSelect}
          inputchangecallback={this.props.handleUserTypedProjectName}
          value={this.props.searchText}
        />
      );
    }
  };

  render() {
    const { classes, sortQueries } = this.props;

    return (
      <div className={classes.search}>
        {this.renderSearchComponent()}
        <div className={classes.sort}>
          <Typography variant="h5" gutterBottom className={classes.label}>
            Sort By:
          </Typography>
          {sortQueries.map((query, index) => (
            <Button
              key={String(index)}
              variant="contained"
              color="primary"
              name={query.id}
              onClick={() => {
                this.props.onSort(query);
              }}
              className={classes.button}
            >
              {query.label}
            </Button>
          ))}
        </div>
      </div>
    );
  }
}

export default withStyles(styles)(SearchAndSortComponent);
